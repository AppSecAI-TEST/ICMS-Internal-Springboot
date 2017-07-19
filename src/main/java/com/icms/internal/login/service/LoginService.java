package com.icms.internal.login.service;

import com.icms.internal.login.controller.LoginController;
import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import com.icms.internal.login.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


/**
 * Created by Infocepts India in 2017.
 */
@Service

public class LoginService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private LoginRepository loginRepository;
    private ApplicationContext applicationContext;

    public LoginService (final LoginRepository loginRepository, final ApplicationContext applicationContext)
    {
        this.loginRepository = loginRepository;
        this.applicationContext = applicationContext;
    }

    public LoginResponse doLogin (final LoginForm loginForm) throws SQLException
    {
        LOGGER.debug(">> " + new Object() {}.getClass().getEnclosingMethod().getName());

        if (loginForm.getUsername().length() == 0 || loginForm.getPassword().length() == 0)
        {
            LOGGER.error("Improper login credentials.");
            LOGGER.error(loginForm.toString());
            LoginResponse loginResponse = this.applicationContext.getBean(LoginResponse.class);
            loginResponse.setErrorMessage("Username Password cannot be Empty.");
            return loginResponse;
        }
        else
        {
            return this.loginRepository.doLogin(loginForm);
        }
    }
}
