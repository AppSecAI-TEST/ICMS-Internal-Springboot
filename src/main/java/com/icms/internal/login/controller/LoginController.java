package com.icms.internal.login.controller;

import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import com.icms.internal.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/Authenticate")
public class LoginController
{
    private LoginService loginService;

    @Autowired
    public LoginController (final LoginService loginService)
    {
        this.loginService = loginService;
    }

    @PostMapping("/doLogin")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody final LoginForm loginForm) throws SQLException
    {

        LoginResponse loginResponse = this.loginService.doLogin(loginForm);

        if( null == loginResponse.getErrorMessage())
        {
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>( loginResponse ,HttpStatus.UNAUTHORIZED );
        }
    }
}
