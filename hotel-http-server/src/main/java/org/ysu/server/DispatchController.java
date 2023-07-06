package org.ysu.server;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.annotation.Get;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.codec.MyHttpRequest;
import org.ysu.ioc.IocContainer;
import org.ysu.utils.FastJsonUtil;
import org.ysu.utils.ReflectionUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

public class DispatchController {
    private static final Set<Class<?>> controllers;
    private static final Map<String, List<Class<?>>> urlMapping = new HashMap<>();
    private static final Map<Class<?>, Map<String, Method>> controllerMethod = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchController.class);

    static {
        controllers = IocContainer.getControllers();
        for (Class<?> controller : controllers) {
            if (controller.isAnnotationPresent(RestController.class)) {
                RestController annotation = controller.getAnnotation(RestController.class);
                String value = annotation.value();
                if(!urlMapping.containsKey(value)){
                    urlMapping.put(value, new ArrayList<Class<?>>());
                }
                urlMapping.get(value).add(controller);
            }
        }
        for (Class<?> controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();
            controllerMethod.put(controller, new HashMap<String, Method>());
            for (Method method : methods) {
                if (method.isAnnotationPresent(Post.class)) {
                    String value = method.getAnnotation(Post.class).value();
                    controllerMethod.get(controller).put(value, method);
                }
            }
        }
    }
    public static Object handlerPath(String path, MyHttpRequest request) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, IntrospectionException {
        Map<String, Object> requestBody = FastJsonUtil.stringToCollect(request.getBody());
        String[] split = path.split("/");
        LOGGER.info("request path : "+ path);
        if(split.length > 0){
            //能请求
            if(!urlMapping.containsKey(split[1])){
                return null;
            }
            List<Class<?>> controllers = urlMapping.get(split[1]);//得到对应的controllers
            for (Class<?> controller : controllers) {
                //有 controller 匹配
                if(controllerMethod.containsKey(controller)){
                    //controller里有接口
                    Map<String, Method> methodMap = controllerMethod.get(controller); //得到对应的method
                    if(split.length > 1){
                        //请求的第二级
                        if(methodMap.containsKey(split[2])){
                            //存在对应的接口
                            Method method = methodMap.get(split[2]);  //找到这个接口
                            Parameter[] parameters = method.getParameters(); //得到参数
                            Object[] args = new Object[parameters.length]; //创建参数传入
                            int index = 0;
                            for (Parameter parameter : parameters) {
                                if (parameter.isAnnotationPresent(RequestParam.class)) {
                                    RequestParam requestParamAnnotation = parameter.getAnnotation(RequestParam.class);
                                    if (!StringUtils.isEmpty(requestParamAnnotation.value())) {
                                        if(requestBody.containsKey(requestParamAnnotation.value())){
//                                            Class<?> var = requestBody.get(requestParamAnnotation.value()).getClass();
//                                            Object instance = parameter.getType().getConstructor().newInstance();
                                            Object instance = requestBody.get(requestParamAnnotation.value());
//                                            Constructor<?> constructor = parameter.getType().getConstructor(var);
//                                            Object instance = constructor.newInstance(requestBody.get(requestParamAnnotation.value()));
                                            args[index] = instance;
                                        }else {
                                            args[index] = null;
                                        }
                                    }else {
                                        args[index] = null;
                                    }
                                }else{
                                    if(requestBody.containsKey(parameter.getName())){
                                        Class<?> var = requestBody.get(parameter.getName()).getClass();
                                        Constructor<?> constructor = parameter.getType().getConstructor(var);
                                        Object instance = constructor.newInstance(requestBody.get(parameter.getName()));
                                        args[index] = instance;
                                    }else{
                                        Class<?> type = parameter.getType();
                                        try{
                                            Object instance = type.newInstance();
                                            Field[] fields = type.getDeclaredFields();
                                            for (Field field : fields) {
                                                if(requestBody.containsKey(field.getName())){
                                                    PropertyDescriptor prop = new PropertyDescriptor(field.getName(), type);
                                                    if(field.getType() == BigDecimal.class){
                                                        prop.getWriteMethod().invoke(instance, new BigDecimal(requestBody.get(field.getName()).toString()));
                                                    }else{
                                                        prop.getWriteMethod().invoke(instance, requestBody.get(field.getName()));
                                                    }
                                                }
                                            }
                                            args[index] = instance;
                                        }catch (Exception e){
                                            args[index] = null;
                                        }
                                    }
                                }
                                index++;
                            }
                            Object result = null;
                            if(args.length == 0){
                                result = method.invoke(IocContainer.getBean(controller));
                            }else{
                                result = method.invoke(IocContainer.getBean(controller), args);
                            }
                            return result;
                        }
                    }
                }
            }
        }
        return "";
    }
}
