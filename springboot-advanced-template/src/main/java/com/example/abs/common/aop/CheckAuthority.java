package com.example.abs.common.aop;

import java.lang.annotation.*;

/**
 * 自定义注解@CheckAuthority
 * 用于将来可能使用的额外权限校验 (如所有权)
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthority {
    boolean authority() default true;   // 作者权限
    boolean admin() default false;      // 管理员权限
}
