package org.ysu.ioc;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.Autowired;
import org.ysu.utils.ClassHelper;
import org.ysu.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class IocContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(IocContainer.class);

    public Map<Class<?>, Object> getIOC() {
        return IOC;
    }

    private static final Map<Class<?>, Object> IOC = new ConcurrentHashMap<>();
    private static final Set<Class<?>> serviceClassSet = ClassHelper.getServiceClassSet();
    private static volatile IocContainer container = null;

    public IocContainer() {

    }

    public static IocContainer getInstance(){
        if(container == null){
            synchronized (IocContainer.class){
                if(container == null){
                    container = new IocContainer();
                }
            }
        }
        return container;
    }
//    private static ConsumerProxy proxy;
//
//    public IocContainer(String centerIp, Integer centerPort) {
//        proxy = new ConsumerProxy(centerIp, centerPort);
//    }

    public static Set<Class<?>> getServiceInterfaceClassSet() {
        return serviceInterfaceClassSet;
    }

    private static final Set<Class<?>> serviceInterfaceClassSet = new HashSet<>();
    private static final Map<String, Class<?>> STRING_IOC = new ConcurrentHashMap<>();
    private static final Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
    private static final Set<Class<?>> serviceRemote = new HashSet<>();

    public void run(){
        Set<Class<?>> serviceClassSet = ClassHelper.getServiceClassSet();
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        for (Class<?> beanClass : serviceClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            IOC.put(beanClass, obj);
            STRING_IOC.put(beanClass.getName(), beanClass);
            LOGGER.info(String.format("scan service %s", beanClass.getName()));
            serviceInterfaceClassSet.add(beanClass.getInterfaces()[0]); //把这个 接口 加入 集合
            IOC.put(beanClass.getInterfaces()[0], obj); //把这个接口 放入 IOC
            STRING_IOC.put(beanClass.getInterfaces()[0].getName(), beanClass.getInterfaces()[0]); //加一个 这个接口对应类的映射
        }
        for (Class<?> beanClass : controllerClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            IOC.put(beanClass, obj);
            LOGGER.info(String.format("scan controller %s", beanClass.getName()));
        }
        dependencyInject();
    }

    public static Set<Class<?>> getControllers(){
        return controllerClassSet;
    }

    public Set<Class<?>> getServiceClassSet(){
        return serviceClassSet;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!IOC.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) IOC.get(cls);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String clzName){
        Class<?> cls = STRING_IOC.get(clzName);
        if (!IOC.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) IOC.get(cls);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        IOC.put(cls, obj);
    }

    public static void dependencyInject() {
        //遍历bean容器里的所有bean
        if (MapUtils.isNotEmpty(IOC)) {
            for (Map.Entry<Class<?>, Object> beanEntry : IOC.entrySet()) {
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
                            //如果beanFieldClass是接口, 就获取接口对应的实现类
                            beanFieldClass = findImplementClass(beanFieldClass);
                            //获取Class类对应的实例
                            Object beanFieldInstance = IOC.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }else{
                                ReflectionUtil.setField(beanInstance, beanField, null);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取接口对应的实现类
     */
    public static Class<?> findImplementClass(Class<?> interfaceClass) {
        Class<?> implementClass = interfaceClass;
        //接口对应的所有实现类
        Set<Class<?>> classSetBySuper = ClassHelper.getClassSetBySuper(interfaceClass);
        if (CollectionUtils.isNotEmpty(classSetBySuper)) {
            //获取第一个实现类
            implementClass = classSetBySuper.iterator().next();
        }
        return implementClass;
    }

//    public static void main(String[] args) {
//        IocContainer iocContainer = new IocContainer();
//        System.out.println(iocContainer);
//    }
}
