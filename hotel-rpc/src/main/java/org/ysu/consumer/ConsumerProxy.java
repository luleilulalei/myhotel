package org.ysu.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.RemoteApplication;
import org.ysu.beans.MethodData;
import org.ysu.beans.RegisterData;
import org.ysu.beans.RegisterTable;
import org.ysu.utils.FastJsonUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConsumerProxy implements MethodInterceptor {
    //    private static RegisterTable registerTable = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerProxy.class);
    private static Map<String, JSONObject> registerTable = null;
    private static ConsumerHandler consumerHandler = new ConsumerHandler();
    ;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
//    private static final String REGISTER_URL = "127.0.0.1";
//    private static final Integer REGISTER_PORT = 9999;
    private String centerIp;
    private Integer centerPort;

    public ConsumerProxy(String centerIp, Integer centerPort) {
        this.centerIp = centerIp;
        this.centerPort = centerPort;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        RemoteApplication annotation = null;
        Class<?>[] interfaces = o.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (anInterface.isAnnotationPresent(RemoteApplication.class)) {
                annotation = anInterface.getAnnotation(RemoteApplication.class);
                break;
            } else {
                return null;
            }
        }

        if (registerTable == null) {
            // 初始化本地注册表
            consumerHandler.setRequestBody("{\"event\":\"GET_SERVICES\"}");
            connectCenter(centerIp, centerPort, consumerHandler);
            String responseJson = executorService.submit(consumerHandler).get();
            Map<Object, Object> map = FastJsonUtil.stringToCollect(responseJson);
            if (!"success".equals(map.get("msg"))) {
                return null;
            }
            String data = (String) (map.get("data"));
            Map<String, JSONObject> table = FastJsonUtil.stringToCollect(data);
            System.out.println(data);
            registerTable = table;
            LOGGER.info("获取服务注册表成功");
            System.out.println(registerTable);
        }

        //封装方法参数
        MethodData methodData = new MethodData(
                annotation.value(),
                o.getClass().getInterfaces()[0].getName(),
                method.getName(),
                objects
        );
        LOGGER.info(methodData.toString());
        consumerHandler.setRequestBody(FastJsonUtil.convertObjectToJSON(methodData));
        JSONObject jsonObject = registerTable.get(annotation.value()); //提供该服务的主机列表
        JSONArray services = jsonObject.getJSONArray("services");
        JSONArray urls = jsonObject.getJSONArray("urls");
        LOGGER.info(services.toJSONString());
        LOGGER.info(urls.toJSONString());
        LOGGER.info(String.valueOf(services.contains(o.getClass().getInterfaces()[0].getName())));
        if (services.contains(o.getClass().getInterfaces()[0].getName())) {
            //如果有该服务
            for (Object urlObj : urls) {
                String url = (String) urlObj;
                String[] split = url.split(":");
                String serviceIp = split[0];
                int servicePort = Integer.parseInt(split[1]);
                consumerHandler.setRequestBody(FastJsonUtil.convertObjectToJSON(methodData));
                LOGGER.info(String.format("连接服务提供方:%s   ip:%s  port:%s", annotation.value(), serviceIp, servicePort));
                connectCenter(serviceIp, servicePort, consumerHandler);
                String returnVal = executorService.submit(consumerHandler).get();
                Map<Object, Object> map = FastJsonUtil.stringToCollect(returnVal);
                if ("success".equals(map.get("msg"))) {
                    return FastJsonUtil.convertJsonToObject((String) map.get("data"), method.getReturnType());
                }
            }
        } else {
            return null;
        }
        return method.getName();
    }


    public Object getInstance(Class<?> clz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public static void connectCenter(String ip, int port, ChannelHandler channelHandler) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(channelHandler);
                    }
                });
        bootstrap.connect(ip, port).sync();
    }

}