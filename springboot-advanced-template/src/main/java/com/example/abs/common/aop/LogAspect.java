package com.example.abs.common.aop;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Order(1)
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(Log)")   //execution(* com.example.abs.service.*Service.*(..))
    private void printLogMethod() {
        /*do nothing*/
    }

    @Before("printLogMethod()")
    public void invoke(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log log2 = methodSignature.getMethod().getAnnotation(Log.class);

        if (null != log2) {
            String now = LocalDateTime.now().toString().split("\\.")[0];
            if (StpUtil.isLogin()) {
                long userId = StpUtil.getLoginIdAsLong();
                log.info(now + "\t"
                        + "user_id=" + userId + "\t"
                        + joinPoint.getTarget().getClass().getName() + "\t"
                        + methodSignature.getMethod().getName() + "\t"
                        + Arrays.asList(joinPoint.getArgs()));
            }
            else {
                log.info(now + "\t"
                        + "__guest__" + "\t"
                        + joinPoint.getTarget().getClass().getName() + "\t"
                        + methodSignature.getMethod().getName() + "\t"
                        + Arrays.asList(joinPoint.getArgs()));
            }
        }
    }
}
