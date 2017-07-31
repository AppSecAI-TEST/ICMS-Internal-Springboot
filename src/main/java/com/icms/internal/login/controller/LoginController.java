package com.icms.internal.login.controller;

import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import com.icms.internal.login.service.LoginService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CacheControl (maxAge = 0)
@RequestMapping ("/api/v1/Authenticate")
public class LoginController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService;

    @Autowired
    public LoginController (final LoginService loginService)
    {
        this.loginService = loginService;
    }

    @PostMapping ("/doLogin")
    public ResponseEntity<LoginResponse> doLogin (@RequestBody final LoginForm loginForm) throws SQLException
    {
        LOGGER.debug(">> " + new Object(){}.getClass().getEnclosingMethod().getName());

        LoginResponse loginResponse = this.loginService.doLogin(loginForm);

        if (null == loginResponse.getErrorMessage())
        {
            LOGGER.info("Successful login");
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else
        {
            LOGGER.error(loginResponse.toString());
            return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
