package org.ysu.provider;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.Autowired;
import org.ysu.annotation.RemoteApplication;
import org.ysu.consumer.ConsumerProxy;
import org.ysu.ioc.IocContainer;
import org.ysu.utils.FastJsonUtil;
import org.ysu.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Provider {
    private static ProviderRegisterHandler providerHandler = new ProviderRegisterHandler();;
    private static final Logger LOGGER = LoggerFactory.getLogger(Provider.class);
//    private static final String REGISTER_URL = "127.0.0.1";
//    private static final Integer REGISTER_PORT = 9999;
//    private static final Integer SERVICE_PORT = 7000;
//    private static final String  APP_NAME = "provider";
    private static final ExecutorService registerExecutorService = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService beatCheckExecutorService = Executors.newSingleThreadScheduledExecutor();

    public Provider(String appName, Integer servicePort, String centerIp, Integer centerPort) {
        this.appName = appName;
        this.servicePort = servicePort;
        this.centerIp = centerIp;
        this.centerPort = centerPort;
    }

    private String appName;
    private Integer servicePort;
    private String centerIp;
    private Integer centerPort;

    private void registerRequest() throws InterruptedException, ExecutionException {
        Set<Class<?>> serviceClassSet = IocContainer.getServiceInterfaceClassSet();
        List<String> services = new ArrayList<>();
        serviceClassSet.forEach(item -> services.add(item.getName()));
        Map<String, Object> params = new HashMap<>();
        params.put("application", appName);
        params.put("event", "REGISTER");
        params.put("services", services);
        params.put("servicePort", servicePort);

        providerHandler.setBody(FastJsonUtil.collectToString(params));
        send(providerHandler, centerIp, centerPort);
    }

    private void beatCheckRequest() throws InterruptedException, ExecutionException {
        Map<String, Object> params = new HashMap<>();
        params.put("application", "provider");
        params.put("event", "REGISTER");
        providerHandler.setBody(FastJsonUtil.collectToString(params));
        send(providerHandler, centerIp, centerPort);
    }

    private void send(ChannelHandler channelHandler, String ip, int port) throws InterruptedException {
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

    /**
     * 注册服务
     */
    public void runRegisterTask(){
        try {
            registerRequest();
            String s = registerExecutorService.submit(providerHandler).get();
            Map<Object, Object> map = FastJsonUtil.stringToCollect(s);
            if("success".equals(map.get("msg"))){
                LOGGER.info("注册成功~~~~");
                runBeadCheckTask();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void runBeadCheckTask(){
        try {
            beatCheckRequest();
            ScheduledFuture<String> schedule = beatCheckExecutorService.schedule(providerHandler, 15, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void initService(int port, final ChannelHandler personalHandler){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(2);

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 3)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(personalHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
            LOGGER.info("开始提供服务");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    /**
     * 初始化服务
     */
    public void initProvider(){
        this.initService(servicePort, new ProviderProvideHandler());
    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Provider provider = new Provider();
//        provider.runRegisterTask();
//        provider.initService(SERVICE_PORT, new ProviderProvideHandler());
    }
}
