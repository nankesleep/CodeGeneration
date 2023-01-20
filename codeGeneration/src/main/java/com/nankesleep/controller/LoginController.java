package com.nankesleep.controller;

import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.User;
import com.nankesleep.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录",description = "登录与退出")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "验证用户登录")
    @ApiImplicitParam(name = "user",value = "User对象")
    public ResponseResult Login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出",notes = "退出用户登录")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
