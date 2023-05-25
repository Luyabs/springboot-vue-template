package com.example.abs.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(1)
@Aspect
@Component
public class CheckAuthorityAspect {
    @Pointcut("@annotation(CheckAuthority)")
    private void printLogMethod() {
        /*do nothing*/
    }

    @Before("printLogMethod()")
    public void invoke(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CheckAuthority checkAuthority = methodSignature.getMethod().getAnnotation(CheckAuthority.class);

        if (null != checkAuthority) {
            log.error("this is authority check from CheckAuthorityAspect");
        }
    }
}
