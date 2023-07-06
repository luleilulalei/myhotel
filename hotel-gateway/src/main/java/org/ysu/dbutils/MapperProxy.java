package org.ysu.dbutils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MapperProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        return null;
    }

    public Object getInstance(Class<?> clz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MapperProxy.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
