package com.kinghao.dian.annotation;

import java.lang.annotation.*;

/**
 * @Author Kinghao
 * @Date 2020/10/5 12:15
 * @Version 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReSubmit {
    /**
     * 延时时间 在延时多久后可以再次提交
     * ime unit is milliseconds
     */
    long value() default 1000;
}
