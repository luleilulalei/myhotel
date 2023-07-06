package org.ysu;


import org.ysu.annotation.Application;

import org.ysu.dbutils.MapperUtil;
import org.ysu.starter.ApplicationStarter;


/**
 * Hello world!
 *
 */
//@EnableDiscovery
@Application //标识这是一个web应用服务
public class App
{
    public static void main(String[] args) {
        ApplicationStarter.run(App.class);
        MapperUtil.run();
    }
}
