package com.example.abs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.abs.common.Result;
import com.example.abs.common.base.BaseController;
import com.example.abs.entity.User;
import com.example.abs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "用户登录登出")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "传入username和password")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        SaTokenInfo token = userService.login(user.getUsername(), user.getPassword());
        return Result.success().data("token", token.getTokenValue());
    }

    @ApiOperation(value = "解析token", notes = "需传入token")
    @GetMapping("/info")
    public Result getInfo(@RequestParam("token") String tokenValue) {
        Map<String, Object> map = userService.info(tokenValue);
        return Result.success().data(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success().message("登出成功");
    }

    @ApiOperation("是否登录")
    @GetMapping("/is_login")
    public Result isLogin() {
        return StpUtil.isLogin() ? Result.success().data("id", StpUtil.getLoginId()) : Result.error().message("未登录");
    }

    @GetMapping("/allll")
    public Result allllll() {
        return Result.success().data("all", userService.list());
    }
}
