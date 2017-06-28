package com.icms.internal.Interviewer.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RegistrationId
{
    private String registrationId;

    public String getRegistrationId ()
    {
        return registrationId;
    }

    public void setRegistrationId (final String registrationId)
    {
        this.registrationId = registrationId;
    }

    @Override
    public String toString ()
    {
        return "RegistrationId{" +
                "registrationId='" + registrationId + '\'' +
                '}';
    }
}
