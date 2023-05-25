package com.example.abs.common.exception.handler;

import com.example.abs.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;

/**
 * 全局异常处理 - MySQL相关异常 (仅限MySQL)
 */
@Slf4j
@RestControllerAdvice
public class MySqlExceptionHandler {
    /**
     * 字符串数据过长
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    private Result dataOutOfRangeException(Exception ex) {
        if (ex.getMessage().trim().startsWith("### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column")) {
            String message = ex.getMessage().split("'")[1] + "属性过长";
            log.error("[DataIntegrityViolationException] " + message);
            return Result.error().message(message);
        }
        if (ex.getMessage().trim().startsWith("### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails")) {
            String message = "不符合外键约束, 请检查传入的属性";
            log.error("[DataIntegrityViolationException] " + message);
            return Result.error().message(message);
        }
        ex.printStackTrace();   // 未知错误
        return Result.error().message(ex.getMessage());
    }

    /**
     * 约束异常
     */
    @ExceptionHandler(UncategorizedSQLException.class)
    private Result uncategorizedPropertyException(Exception ex) {
        if (ex.getMessage().trim().startsWith("### Error updating database.  Cause: java.sql.SQLException: Check constraint ")) {
            String message = ex.getMessage().split(": ")[2].split("\\.")[0];
            log.error("[UncategorizedSQLException] " + " 数据库约束被打破, 请为属性更换合适的值 " + message);
            return Result.error().message(message);
        }
        ex.printStackTrace();   // 未知错误
        return Result.error().message(ex.getMessage());
    }

    /**
     * 数据库连接失败
     */
    @ExceptionHandler({SocketTimeoutException.class, CannotGetJdbcConnectionException.class})
    private Result dbConnectFailedException(Exception ex) {
        log.error("[" + ex.getClass() +  "] 数据库连接失败" + ex.getMessage());
//        ex.printStackTrace();   // 未知错误
        return Result.error().message("连接失败, 请重试");
    }

    /**
     * 主键重复
     */
    @ExceptionHandler(DuplicateKeyException.class)
    private Result duplicatePrimaryKeyException(Exception ex) {
        if (ex.getMessage().trim().startsWith("### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException")) {
            String message = ex.getMessage().split(":")[2].split("###")[0];
            log.error("[UncategorizedSQLException] " + " 主键重复 " + message);
            return Result.error().message("主键重复, 请联系后端修数据库");
        }
        ex.printStackTrace();   // 未知错误
        return Result.error().message(ex.getMessage());
    }

}
