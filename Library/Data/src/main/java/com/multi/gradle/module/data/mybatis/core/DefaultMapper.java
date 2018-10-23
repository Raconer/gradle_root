package com.multi.gradle.module.data.mybatis.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by dhokim on  2018-10-22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DefaultMapper {
    String value() default "";
}
