package com.icms.internal.sendmailtocandidates.controller;

import com.icms.internal.sendmailtocandidates.model.CandidateRegisteredCollege;
import com.icms.internal.sendmailtocandidates.model.MailToAllRegisteredCandidates;
import com.icms.internal.sendmailtocandidates.model.MailToCandidatesFromCollegeForm;
import com.icms.internal.sendmailtocandidates.service.SendMailToCandidatesService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@CacheControl
@RequestMapping ("/api/v1/admin/SendMailToCandidate")
public class SendMailToCandidatesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailToCandidatesController.class);

    private SendMailToCandidatesService sendMailToCandidatesService;

    public SendMailToCandidatesController (final SendMailToCandidatesService sendMailToCandidatesService)
    {
        this.sendMailToCandidatesService = sendMailToCandidatesService;
    }

    @GetMapping("/CandidatesCollegesRegistered")
    public ResponseEntity<List<CandidateRegisteredCollege>> getCollegeNamesFromRegisteredCandidates() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.sendMailToCandidatesService.getCollegeNamesFromRegisteredCandidates(), HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> sendMailToCandidatesFromCollege(@RequestBody MailToCandidatesFromCollegeForm mailToCandidatesFromCollegeForm) throws SQLException
    {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        this.sendMailToCandidatesService.sendMailToCandidatesFromCollege(mailToCandidatesFromCollegeForm);

        return new ResponseEntity<> ( "" , HttpStatus.OK );
    }

    @PostMapping("/All")
    public ResponseEntity<?> sendMailToAllRegisteredCandidates(@RequestBody MailToAllRegisteredCandidates mailToAllRegisteredCandidates) throws SQLException {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        this.sendMailToCandidatesService.sendMailToAllRegisteredCandidates(mailToAllRegisteredCandidates);

        return new ResponseEntity<> ( "" , HttpStatus.OK );

    }
}
