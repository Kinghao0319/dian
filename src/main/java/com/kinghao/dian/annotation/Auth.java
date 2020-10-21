package com.kinghao.dian.annotation;

import com.kinghao.dian.enums.UserType;

import java.lang.annotation.*;

/**
 * @Author Kinghao
 * @Date 2020/10/5 12:08
 * @Version 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    UserType value() default UserType.PERSONAL;
}
