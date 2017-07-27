package com.icms.internal.candidateinterviewstatus.service;

import com.icms.internal.candidateinterviewstatus.controller.CandidateInterviewStatusController;
import com.icms.internal.candidateinterviewstatus.model.CandidatesInterviewStatus;
import com.icms.internal.candidateinterviewstatus.repository.CandidateInterviewStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateInterviewStatusService.class);

    @Autowired
    public CandidateInterviewStatusService (final CandidateInterviewStatusRepository candidateInterviewStatusRepository)
    {
        this.candidateInterviewStatusRepository = candidateInterviewStatusRepository;
    }

    public List<CandidatesInterviewStatus> getInterviewStatusForAll() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.candidateInterviewStatusRepository.getInterviewStatusForAll();
    }
}
