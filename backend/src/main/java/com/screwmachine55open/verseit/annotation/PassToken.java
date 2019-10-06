package com.screwmachine55open.verseit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/10/3 22:00
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
