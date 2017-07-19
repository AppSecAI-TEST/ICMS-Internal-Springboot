package com.icms.internal.candidate.service;

import com.icms.internal.interviewer.contoller.InterviewContoller;
import com.icms.internal.candidate.model.CandidateInfo;
import com.icms.internal.candidate.repopsitory.CandidateRepository;
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
public class CandidateService
{
    private CandidateRepository candidateRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    public CandidateService (final CandidateRepository candidateRepository)
    {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateInfo> getAllCandidateList () throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.candidateRepository.getAllCandidateList();
    }
}
