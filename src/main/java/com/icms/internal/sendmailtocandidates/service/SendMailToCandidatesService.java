package com.icms.internal.sendmailtocandidates.service;

import com.icms.internal.sendmailtocandidates.model.CandidateRegisteredCollege;
import com.icms.internal.sendmailtocandidates.model.MailToAllRegisteredCandidates;
import com.icms.internal.sendmailtocandidates.model.MailToCandidatesFromCollegeForm;
import com.icms.internal.sendmailtocandidates.repository.SendMailToCandidatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SendMailToCandidatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailToCandidatesService.class);
    private SendMailToCandidatesRepository sendMailToCandidatesRepository;
    private ApplicationContext applicationContext;

    @Autowired
    public SendMailToCandidatesService (final SendMailToCandidatesRepository sendMailToCandidatesRepository, final ApplicationContext applicationContext)
    {
        this.sendMailToCandidatesRepository = sendMailToCandidatesRepository;
        this.applicationContext = applicationContext;
    }


    public List<CandidateRegisteredCollege> getCollegeNamesFromRegisteredCandidates() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.sendMailToCandidatesRepository.getCollegeNamesFromRegisteredCandidates();
    }

    public void sendMailToCandidatesFromCollege( MailToCandidatesFromCollegeForm mailToCandidatesFromCollegeForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        this.sendMailToCandidatesRepository.sendMailToCandidatesFromCollege(mailToCandidatesFromCollegeForm);
    }

    public void sendMailToAllRegisteredCandidates(MailToAllRegisteredCandidates mailToAllRegisteredCandidates) throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        this.sendMailToCandidatesRepository.sendMailToAllRegisteredCandidates(mailToAllRegisteredCandidates);
    }

}
