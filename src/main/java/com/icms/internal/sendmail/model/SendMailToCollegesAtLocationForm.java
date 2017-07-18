package com.icms.internal.sendmail.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SendMailToCollegesAtLocationForm
{
    private String city;
    private String mailSubject;
    private String mailBody;

    public String getCity ()
    {
        return city;
    }

    public void setCity (final String city)
    {
        this.city = city;
    }

    public String getMailSubject ()
    {
        return mailSubject;
    }

    public void setMailSubject (final String mailSubject)
    {
        this.mailSubject = mailSubject;
    }

    public String getMailBody ()
    {
        return mailBody;
    }

    public void setMailBody (final String mailBody)
    {
        this.mailBody = mailBody;
    }

    @Override
    public String toString ()
    {
        return "SendMailToCollegesAtLocationForm{" +
                "city='" + city + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailBody='" + mailBody + '\'' +
                '}';
    }
}
