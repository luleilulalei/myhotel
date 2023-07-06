package org.ysu.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.Application;
import org.ysu.server.NettyHttpServer;
import org.ysu.utils.YamlUtil;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class HttpServerStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerStarter.class);
    public static void run(Class<?> clz){
        if(clz.isAnnotationPresent(Application.class)){
            try {
                Map<?, ?> appCfg =  YamlUtil.loadYaml("application.yml");
                Map<String,Object> application = (Map<String,Object>)YamlUtil.getProperty(appCfg, "application");
                Integer port = (Integer)application.get("port");
                NettyHttpServer.startServer("127.0.0.1", port);
            }catch (NullPointerException e){
                LOGGER.error("配置文件写错了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("未被表示为一个应用");
        }
    }
}
