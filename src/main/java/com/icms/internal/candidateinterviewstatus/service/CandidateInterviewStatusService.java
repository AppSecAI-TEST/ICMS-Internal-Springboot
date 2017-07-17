package com.icms.internal.candidateinterviewstatus.service;

import com.icms.internal.candidateinterviewstatus.model.CandidatesInterviewStatus;
import com.icms.internal.candidateinterviewstatus.repository.CandidateInterviewStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class CandidateInterviewStatusService
{
    private CandidateInterviewStatusRepository candidateInterviewStatusRepository;

    @Autowired
    public CandidateInterviewStatusService (final CandidateInterviewStatusRepository candidateInterviewStatusRepository)
    {
        this.candidateInterviewStatusRepository = candidateInterviewStatusRepository;
    }

    public List<CandidatesInterviewStatus> getInterviewStatusForAll() throws SQLException
    {
        return this.candidateInterviewStatusRepository.getInterviewStatusForAll();
    }
}
