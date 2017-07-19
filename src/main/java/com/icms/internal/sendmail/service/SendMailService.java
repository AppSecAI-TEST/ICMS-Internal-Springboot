package com.icms.internal.sendmail.service;

import com.icms.internal.sendmail.model.SendMailToCollegesAtLocationForm;
import com.icms.internal.sendmail.model.SendMailToCollegesForm;
import com.icms.internal.sendmail.repository.SendMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class SendMailService
{
    private SendMailRepository sendMailRepository;

    @Autowired
    public SendMailService (final SendMailRepository sendMailRepository)
    {
        this.sendMailRepository = sendMailRepository;
    }

    public List<String> getCollegeCities() throws SQLException
    {
        return this.sendMailRepository.getCollegeCities();
    }

    public List<String> getCollegeNames() throws SQLException
    {
        return this.sendMailRepository.getCollegeNames();
    }

    public void sendMailToCollegeAtLocation(SendMailToCollegesAtLocationForm sendMailToCollegesAtLocationForm) throws SQLException
    {
        this.sendMailRepository.sendMailToCollegeAtLocation(sendMailToCollegesAtLocationForm);
    }

    public void sendMailToColleges(SendMailToCollegesForm sendMailToCollegesForm) throws SQLException
    {
        this.sendMailRepository.sendMailToColleges(sendMailToCollegesForm);
    }




}
