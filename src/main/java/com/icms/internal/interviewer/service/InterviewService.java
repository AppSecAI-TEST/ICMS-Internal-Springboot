package com.icms.internal.interviewer.service;

import com.icms.internal.interviewer.model.HrInterviewUpdateForm;
import com.icms.internal.interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.interviewer.model.TechnicalInterviewUpdateForm;
import com.icms.internal.interviewer.repository.InterviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class InterviewService
{
    private InterviewRepository interviewRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewService.class);

    @Autowired
    public InterviewService (final InterviewRepository interviewRepository)
    {
        this.interviewRepository = interviewRepository;
    }

    public InterviewCandidiateInfo getCandidateInfo(String candidateRegistrationId) throws Exception{
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.interviewRepository.getCandidateInfo(candidateRegistrationId);
    }

    public boolean updateTechnicalInterviewDetails(final TechnicalInterviewUpdateForm technicalInterviewUpdateForm , final String  interviewerName) throws SQLException{

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.interviewRepository.updateTechnicalInterviewDetails(technicalInterviewUpdateForm, interviewerName);
    }


    public boolean updateHrInterviewDetails (final HrInterviewUpdateForm hrInterviewUpdateForm, final String interviewername) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.interviewRepository.updateHrInterviewDetails(hrInterviewUpdateForm, interviewername);
    }
}
