package com.icms.internal.login.service;

import com.icms.internal.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Infocepts India in 2017.
 */
@Service

public class LoginService
{
    private LoginRepository loginRepository;

    @Autowired
    public LoginService (final LoginRepository loginRepository)
    {
        this.loginRepository = loginRepository;
    }

    public Boolean doLogin(){
        return this.loginRepository.doLogin();
    }
}
