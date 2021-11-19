package com.example.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zouxipu on 2021/3/16.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestPermission {
    String[] permissions();

    int requestCode() default 0;
}
