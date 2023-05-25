package com.example.abs.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisBean {
    @Autowired
    private StringRedisTemplate template;

    public String get(String key) {
        String s = template.opsForValue().get(key);
        return s;
    }


//    public boolean someMethod() {
//        return this.template.hasKey("hello");
//    }

}

