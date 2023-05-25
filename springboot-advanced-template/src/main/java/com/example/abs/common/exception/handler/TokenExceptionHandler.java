package com.example.abs.common.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.abs.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 - Jwt异常处理
 */
@Slf4j
@RestControllerAdvice
public class TokenExceptionHandler {
    /**
     * 未登录 (token不存在)
     */
    @ExceptionHandler(NotLoginException.class)
    private Result notLogin(Exception ex) {
        log.error("[NotLoginException] " + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }

    /**
     * 角色身份异常 (check role)
     */
    @ExceptionHandler(NotRoleException.class)
    private Result wrongRole(Exception ex) {
        log.error("[NotRoleException] " + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }
}
