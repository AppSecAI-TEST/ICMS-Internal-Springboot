package com.icms.internal.login.service;

import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import com.icms.internal.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


/**
 * Created by Infocepts India in 2017.
 */
@Service

public class LoginService
{
    private LoginRepository loginRepository;
    private ApplicationContext applicationContext;

    public LoginService (final LoginRepository loginRepository, final ApplicationContext applicationContext)
    {
        this.loginRepository = loginRepository;
        this.applicationContext = applicationContext;
    }

    public LoginResponse doLogin(final LoginForm loginForm) throws SQLException
    {
        if(loginForm.getUsername().length() == 0 || loginForm.getPassword().length() ==0 ){
            LoginResponse loginResponse = this.applicationContext.getBean(LoginResponse.class);
            loginResponse.setErrorMessage("Username Password cannot be Empty.");
            return loginResponse;
        }else
        {
            return this.loginRepository.doLogin(loginForm);
        }
    }
}
