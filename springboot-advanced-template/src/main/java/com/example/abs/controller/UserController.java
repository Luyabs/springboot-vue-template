package com.example.abs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.abs.common.JwtUtils;
import com.example.abs.common.Result;
import com.example.abs.entity.User;
import com.example.abs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户登录登出")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "传入username和password")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User target = userService.getOne(wrapper);

        if (target == null)
            return Result.error().message("用户不存在");
        if (!user.getPassword().equals(target.getPassword()))
            return Result.error().message("密码错误");

        String token = JwtUtils.generateToken(user.getUsername());
        return Result.success().data("token", token);
    }

    @ApiOperation(value = "解析token", notes = "需传入token")
    @GetMapping("/info")
    public Result getInfo(String token) throws Exception {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);

        User user = userService.getOne(wrapper);
        return Result.success()
                .data("id", user.getId())
                .data("name", user.getUsername())
                .data("avatar", "https://panjiachen.gitee.io/vue-element-admin-site/home.png")
                .data("roles", user.getUsername().equals("admin") ? new String[]{"admin"} : new String[]{"normal_user"});
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }

    @ApiOperation("获取全部用户")
    @GetMapping
    public Result getAll() {
        List<User> users = userService.list();
        return Result.success().data("users", users);
    }
}
