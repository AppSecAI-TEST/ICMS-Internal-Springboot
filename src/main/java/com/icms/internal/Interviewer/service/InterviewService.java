package com.icms.internal.Interviewer.service;

import com.icms.internal.Interviewer.model.HrInterviewUpdateForm;
import com.icms.internal.Interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.Interviewer.model.TechnicalInterviewUpdateForm;
import com.icms.internal.Interviewer.repository.InterviewRepository;
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

    @Autowired
    public InterviewService (final InterviewRepository interviewRepository)
    {
        this.interviewRepository = interviewRepository;
    }

    public InterviewCandidiateInfo getCandidateInfo(String candidateRegistrationId) throws Exception{
        return this.interviewRepository.getCandidateInfo(candidateRegistrationId);
    }

    public boolean updateTechnicalInterviewDetails(final TechnicalInterviewUpdateForm technicalInterviewUpdateForm , final String  interviewerName) throws SQLException{

        //todo perform validation checks over here

        return this.interviewRepository.updateTechnicalInterviewDetails(technicalInterviewUpdateForm, interviewerName);
    }


    public boolean updateHrInterviewDetails (final HrInterviewUpdateForm hrInterviewUpdateForm, final String interviewername) throws SQLException
    {
        //todo perform validation checks over here

        return this.interviewRepository.updateHrInterviewDetails(hrInterviewUpdateForm, interviewername);
    }
}
