package com.icms.internal.sendmail.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SendMailToCollegesForm
{
    private List<String> colleges;
    private String mailSubject;
    private String mailBody;


    public List<String> getColleges ()
    {
        return colleges;
    }

    public void setColleges (final List<String> colleges)
    {
        this.colleges = colleges;
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
        return "SendMailToCollegesForm{" +
                "colleges=" + colleges +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailBody='" + mailBody + '\'' +
                '}';
    }
}
