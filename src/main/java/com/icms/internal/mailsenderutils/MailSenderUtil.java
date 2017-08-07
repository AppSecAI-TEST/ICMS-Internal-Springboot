package com.icms.internal.mailsenderutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MailSenderUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderUtil.class);

    private ApplicationContext applicationContext;

    // Sender's email ID needs to be mentioned
    String from = "CampusConnect@infocepts.com";

    // Recipient's email ID needs to be mentioned.
    String to = "";

    String cc = "CampusConnect@infocepts.com";

    // Assuming you are sending email from localhost
    String host = "10.10.10.125";

    // Get system properties
    Properties properties = System.getProperties();

    // Get the default Session object.
    Session session = Session.getDefaultInstance(properties);

    @Autowired
    public MailSenderUtil(ApplicationContext applicationContext){
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        this.applicationContext =applicationContext;
    }


    private void sendMail(String to, String mailSubject, String mailBody ) {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

            // Set Subject: header field
            message.setSubject(mailSubject);

            // Send the actual HTML message, as big as you like
           message.setContent( mailBody , "text/html");

            LOGGER.debug(String.format("Trying to Send mail To : %s , \n MailSubject : %s \n MailBody : %s ", to, mailSubject, mailBody));

            // Send message
            Transport.send(message);

            LOGGER.info(String.format("Mail Sent Successfully To : %s \n MailSubject : %s \n MailBody : %s ",to, mailSubject, mailBody));

        }catch (MessagingException mex) {
            LOGGER.error(String.format("Mail Sending Failed To : %s \n MailSubject : %s \n MailBody : %s ", to, mailSubject, mailBody));
            LOGGER.error(mex.getMessage() + ":" + mex.getCause());
        }

    }

    public void sendMailToCollegeAndTpo(String college_email , String tpo_email, String mailSubject, String mailBody ) {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            if(null != college_email)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(college_email));

            if(null != tpo_email)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(tpo_email));

            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

            // Set Subject: header field
            message.setSubject(mailSubject);

            // Send the actual HTML message, as big as you like
            message.setContent( mailBody , "text/html");

            LOGGER.debug(String.format("Trying to Send mail To : %s , %s \n MailSubject : %s \n MailBody : %s ", college_email, tpo_email, mailSubject, mailBody));

            // Send message
            Transport.send(message);

            LOGGER.info(String.format("Mail Sent Successfully To : %s , %s \n MailSubject : %s \n MailBody : %s ", college_email, tpo_email, mailSubject, mailBody));

        }catch (MessagingException mex) {
            LOGGER.error(String.format("Mail Sending Failed To : %s , %s \n MailSubject : %s \n MailBody : %s ", college_email, tpo_email, mailSubject, mailBody));
        }

    }

    public void sendMailToCandidates (List<String> emailIds , String mailSubject, String mailBody){

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append(mailBody);
        bodyBuilder.append(System.getProperty("line.separator"));
        bodyBuilder.append(System.getProperty("line.separator"));
        bodyBuilder.append("Above mail Sent to Following Registered Candidates ");

        for(String emailId : emailIds){
            this.sendMail(emailId, mailSubject, mailBody);

            bodyBuilder.append("<br>");
            bodyBuilder.append(emailId);
        }

        this.sendMail("rragashe@infocepts.com","Mail sent to Registered Candidates.",bodyBuilder.toString());
    }


}
