package com.icms.internal.Interviewer.contoller;

import com.icms.internal.Interviewer.model.HrInterviewUpdateForm;
import com.icms.internal.Interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.Interviewer.model.RegistrationId;
import com.icms.internal.Interviewer.model.TechnicalInterviewUpdateForm;
import com.icms.internal.Interviewer.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

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



    @PostMapping("/updateTechnicalInterview")
    public ResponseEntity<Boolean> updateTechnicalInterviewDetails(@RequestBody final TechnicalInterviewUpdateForm technicalInterviewUpdateForm , final HttpServletRequest httpServletRequest) throws SQLException{

        String authToken = httpServletRequest.getHeader("authToken");
        String interviewername = new String(Base64Utils.decode(authToken.getBytes())).split(":")[0];


        if(null == authToken || null == interviewername){
            return new ResponseEntity<>( false ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.interviewService.updateTechnicalInterviewDetails(technicalInterviewUpdateForm,interviewername), HttpStatus.OK );

    }


    @PostMapping("/updateHrInterview")
    public ResponseEntity<Boolean> updateHrInterviewDetails(@RequestBody final HrInterviewUpdateForm hrInterviewUpdateForm , final HttpServletRequest httpServletRequest) throws SQLException{

        String authToken = httpServletRequest.getHeader("authToken");
        String interviewername = new String(Base64Utils.decode(authToken.getBytes())).split(":")[0];


        if(null == authToken || null == interviewername){
            return new ResponseEntity<>( false ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.interviewService.updateHrInterviewDetails(hrInterviewUpdateForm,interviewername), HttpStatus.OK );

    }



}
