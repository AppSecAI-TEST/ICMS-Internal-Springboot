package com.icms.internal.sendmail.controller;

import com.icms.internal.sendmail.model.SendMailToCollegesAtLocationForm;
import com.icms.internal.sendmail.service.SendMailService;
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
@RequestMapping("/api/v1/SendMail")
public class SendMailController
{
    private SendMailService sendMailService;

    @Autowired
    public SendMailController (final SendMailService sendMailService)
    {
        this.sendMailService = sendMailService;
    }

    @GetMapping("/collegeLocations")
    public ResponseEntity<List<String>> getCollegeCities() throws SQLException
    {
        return new ResponseEntity<>(this.sendMailService.getCollegeCities(), HttpStatus.OK );
    }

    @PostMapping("/ToCollegeAtLocation")
    public ResponseEntity<?> sendMailToCollegeAtLocation(@RequestBody SendMailToCollegesAtLocationForm sendMailToCollegesAtLocationForm){

        System.out.println(sendMailToCollegesAtLocationForm);

        return new ResponseEntity<Object>("", HttpStatus.OK);
    }

    @PostMapping("/ToColleges")
    public void sendMailToCollege(@RequestBody List<String> colleges){
        System.out.println(colleges);

    }
}
