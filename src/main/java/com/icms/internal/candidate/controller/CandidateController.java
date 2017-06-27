package com.icms.internal.candidate.controller;

import com.icms.internal.candidate.model.CandidateInfo;
import com.icms.internal.candidate.service.CandidateService;
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
@RequestMapping ("/api/vi/Candidate")
public class CandidateController
{
    private CandidateService candidateService;

    @Autowired
    public CandidateController (final CandidateService candidateService)
    {
        this.candidateService = candidateService;
    }

    @GetMapping("/List")
    public ResponseEntity<List<CandidateInfo>> getAllCandidateList() throws SQLException
    {
        return new ResponseEntity<>(this.candidateService.getAllCandidateList(), HttpStatus.OK);
    }
}
