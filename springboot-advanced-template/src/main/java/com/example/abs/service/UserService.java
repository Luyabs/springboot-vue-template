package com.example.abs.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.example.abs.entity.User;
import com.example.abs.common.base.BaseService;

import java.util.Map;

public interface UserService extends BaseService<User> {
    SaTokenInfo login(String username, String password);

    Map<String, Object> info(String tokenValue);

}
