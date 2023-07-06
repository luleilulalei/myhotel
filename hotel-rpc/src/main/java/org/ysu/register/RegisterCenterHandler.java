package org.ysu.register;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.beans.RegisterData;
import org.ysu.utils.FastJsonUtil;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ChannelHandler.Sharable
public class RegisterCenterHandler extends SimpleChannelInboundHandler<String> {
    private static Map<String, RegisterData> registerTable = new ConcurrentHashMap<>();
    private static Map<String, String> urlAppMap = new ConcurrentHashMap<>();
    private static Map<String, Long> urlServiceTimeMap = new ConcurrentHashMap<>();
    private static Map<String, String> originServiceUrlMap = new ConcurrentHashMap<>();
    private static final Lock lock = new ReentrantLock();
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCenterHandler.class);

    static {
        beatCheckTask(); //心跳检测开启
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String body) throws Exception {
        Map<Object, Object> requestBody = FastJsonUtil.stringToCollect(body);
        String responseBody = "{}";
        switch ((String) requestBody.get("event")){
            case "REGISTER":
                String application = (String)requestBody.get("application");
                String remoteUrl = ctx.channel().remoteAddress().toString();
                String url =  remoteUrl.substring(1).split(":")[0] + ":"+requestBody.get("servicePort");
                originServiceUrlMap.put(ctx.channel().remoteAddress().toString(), url);
                JSONArray services = (JSONArray) requestBody.get("services");
                Set<String> objects = new HashSet<>();
                services.forEach(item -> objects.add((String) item));
                this.register(application, url, objects);
                responseBody = "{\"msg\":\"success\"}";
                break;
            case "GET_SERVICES":
                Map<String, Object> res = new HashMap<>();
                res.put("msg", "success");
                res.put("data", JSONObject.toJSONString(registerTable));
                responseBody= FastJsonUtil.collectToString(res);
                LOGGER.info(String.format("%s 请求了服务列表", ctx.channel().remoteAddress().toString()));
                break;
            case "BEAT_CHECK":
//                urlServiceTimeMap.put(ctx.channel().remoteAddress().toString(), System.currentTimeMillis());
                remoteUrl = ctx.channel().remoteAddress().toString();
                url =  remoteUrl.substring(1).split(":")[0] + ":"+requestBody.get("servicePort");
                urlServiceTimeMap.put(url, System.currentTimeMillis());
                responseBody = "{\"msg\":\"success\"}";
                break;
            default:
                break;
        }
        ctx.writeAndFlush(responseBody);
    }

    private void register(String application, String url, Set<String> services){
        try {
            lock.lock();
            if(!registerTable.containsKey(application)){
                registerTable.put(application, new RegisterData(application, services));
                urlAppMap.put(url, application);
                urlServiceTimeMap.put(url, System.currentTimeMillis());
                LOGGER.info(String.format("%s  %s 应用注册了下列服务： ", url, application));
                services.forEach(LOGGER::info);
            }
            RegisterData registerData = registerTable.get(application);
            registerData.addUrl(url);
        }finally {
            lock.unlock();
        }
    }

    private static void beatCheckTask(){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> {
            //15秒一次的心跳检测
            urlServiceTimeMap.forEach((key, value) -> {
                if ((value - System.currentTimeMillis() > 60)) {
                    String app = urlAppMap.get(key); //取出这个 url 对应的 app
                    RegisterData registerData = registerTable.get(app); //取出 应用
                    registerData.removeUrl(key); //把这个应用的 urls 里把这个 url 删除
                    if(registerData.isEmpty()){
                        //如果这个应用对应的 url 空了，就把这个应用移除~~~~
                        registerTable.remove(registerData.getApplication());
                    }
                    urlServiceTimeMap.remove(key);//从这个时间 map 里移除
                }
            });
        }, 15, TimeUnit.SECONDS); //每15秒1次心跳检测
    }

    /**
     * 服务下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress address = ctx.channel().remoteAddress();
        String serviceUrl = originServiceUrlMap.get(address.toString());
        String app = urlAppMap.get(serviceUrl);
        urlAppMap.remove(serviceUrl);
        registerTable.get(app).removeUrl(serviceUrl);
        if(registerTable.get(app).isEmpty()){
            registerTable.remove(app);
            LOGGER.info(app + ": 已无主机提供服务~~~");
        }
        LOGGER.info(app + ": " + ctx.channel().remoteAddress() + "  下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}