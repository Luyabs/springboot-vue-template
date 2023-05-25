package com.example.abs.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.abs.common.exception.exception.NotExistException;
import com.example.abs.entity.User;
import com.example.abs.mapper.UserMapper;
import com.example.abs.service.UserService;
import com.example.abs.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public SaTokenInfo login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        User target = userMapper.selectOne(wrapper);

        if (target == null)
            throw new NotExistException("用户不存在");
        if (!password.equals(target.getPassword()))
            throw new NotExistException("密码不正确");

        StpUtil.login(target.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        tokenInfo.setTokenName("token");
        return tokenInfo;
    }

    @Override
    public Map<String, Object> info(String tokenValue) {
        long id =  Long.parseLong((String) StpUtil.getLoginIdByToken(tokenValue));
//        long id = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectById(id);

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getUsername());
        map.put("avatar", "https://panjiachen.gitee.io/vue-element-admin-site/home.png");
        map.put("roles", user.getUsername().equals("admin") ? new String[]{"admin"} : new String[]{"normal_user"});
        return map;
    }
}
