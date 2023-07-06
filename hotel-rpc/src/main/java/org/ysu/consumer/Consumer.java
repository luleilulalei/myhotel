package org.ysu.consumer;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.ysu.annotation.Autowired;
import org.ysu.annotation.RemoteApplication;
import org.ysu.ioc.IocContainer;
import org.ysu.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class Consumer {
    private static ConsumerProxy consumerProxy;

    public Consumer(String centerIp, Integer centerPort) {
        consumerProxy = new ConsumerProxy(centerIp, centerPort);
    }


    public void proxyInject(){
        //注入代理类
        IocContainer iocContainer = IocContainer.getInstance();
//        public static void dependencyInject() {
        //遍历bean容器里的所有bean
        if (MapUtils.isNotEmpty(iocContainer.getIOC())) {
            for (Map.Entry<Class<?>, Object> beanEntry : iocContainer.getIOC().entrySet()) {
                //bean的class类
                Class<?> beanClass = beanEntry.getKey();
                if(beanClass.isInterface()){
                    continue;
                }
                //bean的实例
                Object beanInstance = beanEntry.getValue();
                //暴力反射获取属性
                Field[] beanFields = beanClass.getDeclaredFields();
                //遍历bean的属性
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        //判断属性是否带Autowired注解
                        if (beanField.isAnnotationPresent(Autowired.class)) {
                            //属性类型
                            Class<?> beanFieldClass = beanField.getType();
                            if (beanFieldClass.isAnnotationPresent(RemoteApplication.class)){
                                // RPC 替换 代理对象
                                Object instance = consumerProxy.getInstance(beanFieldClass);
                                if (instance != null) {
                                    ReflectionUtil.setField(beanInstance, beanField, instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
