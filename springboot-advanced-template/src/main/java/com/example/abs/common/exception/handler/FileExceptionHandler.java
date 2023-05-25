package com.example.abs.common.exception.handler;

import com.example.abs.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;

/**
 * 全局异常处理 - 文件传输异常
 */
@Slf4j
@RestControllerAdvice
public class FileExceptionHandler {
    /**
     * 下载/上传文件出错
     */
    @ExceptionHandler(MultipartException.class)
    public Result uploadException(Exception ex){
        log.error("[MultipartException] " + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }

    /**
     * 文件不存在
     */
    @ExceptionHandler(FileNotFoundException.class)
    public Result fileNotExistException(Exception ex) {
        log.error("[FileNotFoundException] " + ex.getMessage());
        return Result.error().message(ex.getMessage());
    }


}
