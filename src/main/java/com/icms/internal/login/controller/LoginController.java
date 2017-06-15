package com.icms.internal.login.controller;

import com.icms.internal.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Infocepts India in 2017.
 */
@RestController

public class LoginController
{
    private LoginService loginService;

    @Autowired
    public LoginController (final LoginService loginService)
    {
        this.loginService = loginService;
    }

    @GetMapping("/admin/test")
    public String test(){
        return "ADMIN";
    }

    @GetMapping("/user/test")
    public String test1(){
        return "USER";
    }

    @GetMapping("/user/logout")
    public String test3(){
        return "Logged out";
    }
}
