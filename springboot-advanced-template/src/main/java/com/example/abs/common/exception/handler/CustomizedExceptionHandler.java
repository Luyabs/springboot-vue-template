package com.example.abs.common.exception.handler;

import com.example.abs.common.Result;
import com.example.abs.common.exception.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 - 自定义异常类
 */
@Slf4j
@RestControllerAdvice
public class CustomizedExceptionHandler {
    /**
     * 自定义异常类 ServiceException 捕获
     */
    @ExceptionHandler({ServiceException.class})
    private Result customException(Exception ex) {
//        ex.printStackTrace();
        log.error("[ServiceException] " + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }
}
