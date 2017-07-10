package com.icms.internal.usermanagement.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AddUserForm
{
    private String username;
    private String role;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (final String username)
    {
        this.username = username;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (final String role)
    {
        this.role = role;
    }

    @Override
    public String toString ()
    {
        return "AddUserForm{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
