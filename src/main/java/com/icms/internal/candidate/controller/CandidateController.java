package com.icms.internal.candidate.controller;

import com.icms.internal.interviewer.contoller.InterviewContoller;
import com.icms.internal.candidate.model.CandidateInfo;
import com.icms.internal.candidate.service.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */

@RestController
@CrossOrigin
@RequestMapping ("/api/v1/Candidate")
public class CandidateController
{
    private CandidateService candidateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewContoller.class);

    @Autowired
    public CandidateController (final CandidateService candidateService)
    {
        this.candidateService = candidateService;
    }

    @GetMapping("/List")
    public ResponseEntity<List<CandidateInfo>> getAllCandidateList() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.candidateService.getAllCandidateList(), HttpStatus.OK);
    }
}
