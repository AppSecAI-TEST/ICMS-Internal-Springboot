package com.icms.internal.Interviewer.contoller;

import com.icms.internal.Interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.Interviewer.model.RegistrationId;
import com.icms.internal.Interviewer.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@RequestMapping ("/api/v1/Interviewer")
public class InterviewContoller
{

    private InterviewService interviewService;

    @Autowired
    public InterviewContoller (final InterviewService interviewService)
    {
        this.interviewService = interviewService;
    }

    @PostMapping("/InterviewCandidateInfo")
    public ResponseEntity<InterviewCandidiateInfo> getCandidateInfo(@RequestBody final RegistrationId candidateRegistrationId) throws Exception{

        InterviewCandidiateInfo interviewCandidiateInfo = this.interviewService.getCandidateInfo(candidateRegistrationId.getRegistrationId());

        return new ResponseEntity<>( interviewCandidiateInfo , interviewCandidiateInfo == null ? HttpStatus.NOT_FOUND : HttpStatus.OK );

    }
}
