package com.icms.internal.sendmailtocandidates.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MailToAllRegisteredCandidates
{
    private String mailSubject;
    private String mailBody;

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
        return "MailToAllRegisteredCandidates{" +
                "mailSubject='" + mailSubject + '\'' +
                ", mailBody='" + mailBody + '\'' +
                '}';
    }
}
