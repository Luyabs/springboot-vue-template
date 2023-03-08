package com.example.abs.common.exception;

import com.example.abs.common.Result;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 自定义异常类
    @ExceptionHandler({ServiceException.class})
    private Result customException(RuntimeException ex) {
        log.error(ex.getMessage());
        return Result.error().message(ex.getMessage());
    }

    // UNIQUE约束 异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    private Result uniqueKeyRepeat(Exception ex) {
        String errorProperty = ex.getMessage().split(" ")[12].split("'")[1];
        errorProperty = errorProperty.replaceFirst("PRIMARY", "id");   //额外判断主键

        log.error(errorProperty + "在表中已存在, 请更换" + errorProperty);
        return Result.error().message(errorProperty + "在表中已存在, 请更换" + errorProperty);
    }

    // NOT NULL约束 异常
    @ExceptionHandler(SQLException.class)
    private Result notNullConstraint(Exception ex) {
//        ex.printStackTrace();
        String msg = "字段" + ex.getMessage().split(" ")[8] + "不能为空";
        log.error("SQLException: " + msg);
        return Result.error().message(msg);
    }

    @ExceptionHandler({SignatureException.class})
    private Result wrongToken() {
        log.error("token错误");
        return Result.error().message("token错误");
    }

    @ExceptionHandler(JsonParseException.class)
    private Result wrongJSON(Exception ex) {
        log.error(ex.getMessage());
        return Result.error().message("json格式错误");
    }

    @ExceptionHandler(NullPointerException.class)
    private Result nullPointer(Exception ex) {
        log.error("NullPointerException");
        if (ex.getMessage().contains("Cannot invoke \"java.lang.Integer.intValue()\""))
            return Result.error().message("你可能传入了一个空的JSON [int属性字段为空]");
        ex.printStackTrace();
        return Result.error().message("NullPointerException");
    }



    @ExceptionHandler(SQLSyntaxErrorException.class)
    private Result onlyId() {
        log.error("SQLSyntaxErrorException");
        return Result.error().message("SQLSyntaxErrorException, 可能只传入了一个id/字段名不存在-可能拼写错误");
        // 此错误与数据库关系密切
    }
}
