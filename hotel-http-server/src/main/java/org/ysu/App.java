package org.ysu;

import org.ysu.annotation.Application;
import org.ysu.bootstrap.HttpServerStarter;

/**
 * Hello world!
 *
 */
@Application  //标识是一个 http server
public class App 
{
    public static void main( String[] args )
    {
        HttpServerStarter.run(App.class);
    }
}
