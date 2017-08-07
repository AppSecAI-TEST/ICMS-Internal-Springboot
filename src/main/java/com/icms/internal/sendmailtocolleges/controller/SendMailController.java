package com.icms.internal.sendmailtocolleges.controller;

import com.icms.internal.sendmailtocolleges.model.SendMailToCollegesAtLocationForm;
import com.icms.internal.sendmailtocolleges.model.SendMailToCollegesForm;
import com.icms.internal.sendmailtocolleges.service.SendMailService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Controller
@CrossOrigin
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/admin/SendMail")
public class SendMailController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailController.class);
    private SendMailService sendMailService;

    @Autowired
    public SendMailController (final SendMailService sendMailService)
    {
        this.sendMailService = sendMailService;
    }

    @GetMapping("/collegeLocations")
    public ResponseEntity<List<String>> getCollegeCities() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.sendMailService.getCollegeCities(), HttpStatus.OK );
    }

    @GetMapping("/CollegeNames")
    public ResponseEntity<List<String>> getCollegeNames() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.sendMailService.getCollegeNames(), HttpStatus.OK);
    }

    @PostMapping("/ToCollegeAtLocation")
    public ResponseEntity<?> sendMailToCollegeAtLocation(@RequestBody SendMailToCollegesAtLocationForm sendMailToCollegesAtLocationForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        this.sendMailService.sendMailToCollegeAtLocation(sendMailToCollegesAtLocationForm);
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }

    @PostMapping("/ToColleges")
    public ResponseEntity<?> sendMailToCollege(@RequestBody SendMailToCollegesForm sendMailToCollegesForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        this.sendMailService.sendMailToColleges(sendMailToCollegesForm);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
