package org.ysu.dbutils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.ioc.IocContainer;
import org.ysu.utils.ReflectionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class MapperUtil {
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static SqlSession sqlSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtil.class);

    public static void run(){
        init();
        mapperInject();
        proxyInject();
    }

    public static void init(){
        try {

            InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG);
            //SqlSessionFactoryBuilder（构造器）：它会根据配置或者代码来生成SqlSessionFactory，采用的是分步构建的Builder模式。
            //SqlSessionFactory（工厂接口）：依靠它来生成SqlSession，使用的是工厂模式。
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//            sqlSessionFactory.getConfiguration().setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
            //SqlSession（会话）：一个既可以发送SQL执行返回结果，也可以获取Mapper的接口。在现有的技术中，一般我们会让其在业务逻辑代码中“消失”，而使用的是MyBatis提供的SQL Mapper接口编程技术，它能提高代码的可读性和可维护性。
            sqlSession = sqlSessionFactory.openSession();
            //SQL Mapper（映射器）：MyBatis新设计存在的组件，它由一个Java接口和XML文件（或注解）构成，需要给出对应的SQL和映射规则。它负责发送SQL去执行，并返回结果。
//            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        return sqlSession;
    }

    public static void mapperInject(){
        //注入代理类
        IocContainer iocContainer = IocContainer.getInstance();
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
                        if (beanField.isAnnotationPresent(Mapper.class)) {
                            //属性类型
                            Class<?> beanFieldClass = beanField.getType();
                            Object mapper = sqlSession.getMapper(beanFieldClass);
                            ReflectionUtil.setField(beanInstance, beanField, mapper);
                            LOGGER.info(String.format("scan mapper %s", beanFieldClass));
                        }
                    }
                }
            }
        }
    }

    /**
     * 注入代理
     */
    public static void proxyInject(){
        IocContainer container = IocContainer.getInstance();
        TransactionProxy transactionProxy = new TransactionProxy();
        Set<Class<?>> serviceClassSet = container.getServiceClassSet();
        serviceClassSet.forEach(service -> {
            Object proxyInstance = transactionProxy.getInstance(service);
            LOGGER.info(String.format("scan service proxy %s", proxyInstance.toString()));
            container.getIOC().put(service,proxyInstance);
        });
    }
}
