package org.ysu.starter;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.Application;
import org.ysu.annotation.EnableDiscovery;
import org.ysu.annotation.RegisterCenter;
import org.ysu.consumer.Consumer;
import org.ysu.ioc.IocContainer;
import org.ysu.provider.Provider;
import org.ysu.register.RegisterCenterStarter;
import org.ysu.server.NettyHttpServer;
import org.ysu.utils.YamlUtil;

import java.util.Map;

public class ApplicationStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStarter.class);


    @SuppressWarnings("unchecked")
    public static void run(Class<?> clz){
        if(clz.isAnnotationPresent(Application.class)){
            IocContainer container = null;
            Map<?, ?> appConfig =  YamlUtil.loadYaml("application.yml");
            Map<String,Object> appCfg = (Map<String,Object>)YamlUtil.getProperty(appConfig, "application");
            Map<String,Object> centerCfg = (Map<String,Object>)YamlUtil.getProperty(appConfig, "center");
            final String appName = MapUtils.getString(appCfg, "name");
            final String appIp = MapUtils.getString(appCfg, "ip");
            final Integer httpPort = MapUtils.getInteger(appCfg, "port");
            final String centerIp = MapUtils.getString(appCfg, "name");
            final Integer centerPort = MapUtils.getInteger(appCfg, "port");
            try {
                container = IocContainer.getInstance();
                container.run(); //初始化 IOC 容器
                new Thread(() -> {
                    try {
                        NettyHttpServer.startServer(appIp, httpPort);//启动 HTTP 服务
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "").start();
            }catch (NullPointerException e){
                LOGGER.error("配置文件写错了");
            }
            if(clz.isAnnotationPresent(EnableDiscovery.class)){
                Consumer consumer = new Consumer(centerIp, centerPort);
                consumer.proxyInject();
                Map<String,Object> proCfg = (Map<String,Object>)YamlUtil.getProperty(appConfig, "provider");
                Integer servicePort = MapUtils.getInteger(proCfg, "port");

                Provider provider = new Provider(appName, servicePort, centerIp, centerPort);
//                provider.proxyInject(); //注入 rpc 代理类
                provider.runRegisterTask();
                provider.initProvider();
            }
        }else if(clz.isAnnotationPresent(RegisterCenter.class)){
            Map<?, ?> appConfig =  YamlUtil.loadYaml("application.yml");
            Map<String,Object> centerCfg = (Map<String,Object>)YamlUtil.getProperty(appConfig, "center");
            Integer port = MapUtils.getInteger(centerCfg, "port");
            new RegisterCenterStarter().start(port);
        }
    }
}
