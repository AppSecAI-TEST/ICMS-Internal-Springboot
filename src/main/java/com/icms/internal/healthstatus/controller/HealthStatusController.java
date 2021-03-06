package com.icms.internal.healthstatus.controller;

import com.icms.internal.healthstatus.service.HealthStatusService;
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

/**
 * Created by matth on 7/15/2017.
 */
@RestController
@CrossOrigin
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/admin/HealthStatus")
public class HealthStatusController {

    private HealthStatusService healthStatusService;
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthStatusController.class);

    @GetMapping("/isDbUp")
    public ResponseEntity<?> isDbUp(){

        return new ResponseEntity<Object>("",this.healthStatusService.isDbUp() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }

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