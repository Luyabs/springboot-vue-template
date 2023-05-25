package com.example.abs.common.exception.handler;

import com.example.abs.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

/**
 * 全局异常处理 - 全局性质问题
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, SignatureException.class})
    private Result argumentOrSomethingError(Exception ex) {
//        ex.printStackTrace();
        log.error("[" + ex.getClass() + "]" + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }

    /**
     * 属性不对
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private Result jsonParseErrorException(Exception ex) {
        String message = ex.getMessage();
        if (ex.getMessage().trim().startsWith("JSON parse error"))
            message = ex.getMessage().split(": ")[1].split(";")[0];
        else if (ex.getMessage().trim().startsWith("Required request body is missing"))
            message = "需要以JSON格式传入参数";
        else
            ex.printStackTrace();   // 未知错误
        log.error("[HttpMessageNotReadableException] " + message);
        return Result.error().message(message);
    }

    /**
     * 空指针异常 应当直接处理
     */
    @ExceptionHandler(NullPointerException.class)
    private Result nullPointerException(Exception ex) {
        ex.printStackTrace();   // 未知错误, 应当直接进行处理
        return Result.error().message("空指针异常, 联系后端修复bug");
    }
}
