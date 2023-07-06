package org.ysu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Application {
    String value() default ""; //基础包名，从这个包下开始扫描
    String basePkg() default ""; //基础包名，从这个包下开始扫描
}
