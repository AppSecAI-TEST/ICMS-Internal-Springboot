package com.icms.internal.HealthStatus.controller;

import com.icms.internal.HealthStatus.service.HealthStatusService;
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

/**
 * Created by matth on 7/15/2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/HealthStatus")
public class HealthStatusController {

    private HealthStatusService healthStatusService;
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthStatusController.class);


    @Autowired
    public HealthStatusController(HealthStatusService healthStatusService) {
        this.healthStatusService = healthStatusService;
    }

    @GetMapping("/totalCandidatesRegistered")
    public ResponseEntity<String> getTotalCandidatesRegistered() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.healthStatusService.getTotalCandidatesRegistered(), HttpStatus.OK);
    }

    @GetMapping("/totalCandidatesToInterview")
    public ResponseEntity<String> getTotalCandidatesToInterview() throws SQLException{
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>( this.healthStatusService.getTotalCandidatesToInterview() , HttpStatus.OK);
    }

    @GetMapping("/totalCandidatesInterviewed")
    public ResponseEntity<String> getTotalCandidatesInterviewed() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.healthStatusService.getTotalCandidatesInterviewed(), HttpStatus.OK );
    }

    @GetMapping("/totalCandidatesSelected")
    public ResponseEntity<String> getTotalCandidatesSelected() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.healthStatusService.getTotalCandidatesSelected(), HttpStatus.OK);
    }
}
