package com.example.abs.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据一致性
 */
@Data
public class Result {
    public static Integer SUCCESS = 20000; //成功

    public static Integer ERROR = 20001; //失败

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

//    private Map map = new HashMap<>();  //动态数据 For Redis

    private Result(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static Result success() {
        return new Result(true, SUCCESS, "成功");
    }

    //失败静态方法
    public static Result error() {
        return new Result(false, ERROR, "失败");
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
