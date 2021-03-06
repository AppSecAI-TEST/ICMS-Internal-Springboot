package com.icms.internal.candidateinterviewstatus.controller;

import com.icms.internal.candidateinterviewstatus.model.CandidatesInterviewStatus;
import com.icms.internal.candidateinterviewstatus.service.CandidateInterviewStatusService;
import net.rossillo.spring.web.mvc.CacheControl;
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
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/admin/CandidateInterviewStatus")
public class CandidateInterviewStatusController
{
    private CandidateInterviewStatusService candidateInterviewStatusService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateInterviewStatusController.class);

    @Autowired
    public CandidateInterviewStatusController (final CandidateInterviewStatusService candidateInterviewStatusService)
    {
        this.candidateInterviewStatusService = candidateInterviewStatusService;
    }

    @GetMapping("/All")
    public ResponseEntity<List<CandidatesInterviewStatus>> getInterviewStatusForAll() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return new ResponseEntity<> (this.candidateInterviewStatusService.getInterviewStatusForAll(), HttpStatus.OK);
    }
}
