package com.icms.internal.login.models;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginForm
{
    private String username;
    private String password;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (final String username)
    {
        this.username = username;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (final String password)
    {
        this.password = password;
    }
}
