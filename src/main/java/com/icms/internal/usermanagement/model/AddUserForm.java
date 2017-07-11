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
    private String userEmail;
    private String role;

    public String getUserEmail ()
    {
        return userEmail;
    }

    public void setUserEmail (final String userEmail)
    {
        this.userEmail = userEmail;
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
                "userEmail='" + userEmail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
