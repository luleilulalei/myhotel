package org.ysu.dbutils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        if(method.isAnnotationPresent(Transaction.class)){
            try {
                result = method.invoke(objects);
            } catch (Exception e) {
                MapperUtil.getSqlSession().rollback();
            }
            MapperUtil.getSqlSession().commit();
        }else {
            result = method.invoke(objects);
        }
        return result;
    }

    public Object getInstance(Class<?> clz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
