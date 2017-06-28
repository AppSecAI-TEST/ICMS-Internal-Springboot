package com.icms.internal.Interviewer.service;

import com.icms.internal.Interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.Interviewer.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
