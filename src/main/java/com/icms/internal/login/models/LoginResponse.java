package com.icms.internal.login.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse
{
    private String username;
    private String authToken;
    private String authRole;
    private String errorMessage = null;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (final String username)
    {
        this.username = username;
    }

    public String getAuthToken ()
    {
        return authToken;
    }

    public void setAuthToken (final String authToken)
    {
        this.authToken = authToken;
    }

    public String getAuthRole ()
    {
        return authRole;
    }

    public void setAuthRole (final String authRole)
    {
        this.authRole = authRole;
    }

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", authToken='" + authToken + '\'' +
                ", authRole='" + authRole + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
