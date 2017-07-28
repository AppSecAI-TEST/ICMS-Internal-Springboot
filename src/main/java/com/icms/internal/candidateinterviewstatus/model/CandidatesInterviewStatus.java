package com.icms.internal.candidateinterviewstatus.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CandidatesInterviewStatus
{
    private String candidateId;
    private String candidateRegTimeStamp;
    private String candidateName;
    private String candidateLastName;
    private String candidateTechnicalClearance;
    private String candidateTechnicalInterviewer;
    private String candidateHrClearance;
    private String candidateHrInterviewer;

    public String getCandidateId ()
    {
        return candidateId;
    }

    public void setCandidateId (final String candidateId)
    {
        this.candidateId = candidateId;
    }

    public String getCandidateRegTimeStamp ()
    {
        return candidateRegTimeStamp;
    }

    public void setCandidateRegTimeStamp (final String candidateRegTimeStamp)
    {
        this.candidateRegTimeStamp = candidateRegTimeStamp;
    }

    public String getCandidateName ()
    {
        return candidateName;
    }

    public void setCandidateName (final String candidateName)
    {
        this.candidateName = candidateName;
    }

    public String getCandidateLastName ()
    {
        return candidateLastName;
    }

    public void setCandidateLastName (final String candidateLastName)
    {
        this.candidateLastName = candidateLastName;
    }

    public String getCandidateTechnicalClearance ()
    {
        return candidateTechnicalClearance;
    }

    public void setCandidateTechnicalClearance (final String candidateTechnicalClearance)
    {
        this.candidateTechnicalClearance = candidateTechnicalClearance;
    }

    public String getCandidateTechnicalInterviewer ()
    {
        return candidateTechnicalInterviewer;
    }

    public void setCandidateTechnicalInterviewer (final String candidateTechnicalInterviewer)
    {
        this.candidateTechnicalInterviewer = candidateTechnicalInterviewer;
    }

    public String getCandidateHrClearance ()
    {
        return candidateHrClearance;
    }

    public void setCandidateHrClearance (final String candidateHrClearance)
    {
        this.candidateHrClearance = candidateHrClearance;
    }

    public String getCandidateHrInterviewer ()
    {
        return candidateHrInterviewer;
    }

    public void setCandidateHrInterviewer (final String candidateHrInterviewer)
    {
        this.candidateHrInterviewer = candidateHrInterviewer;
    }

    @Override
    public String toString ()
    {
        return "CandidatesInterviewStatus{" +
                "candidateId='" + candidateId + '\'' +
                ", candidateRegTimeStamp='" + candidateRegTimeStamp + '\'' +
                ", candidateName='" + candidateName + '\'' +
                ", candidateLastName='" + candidateLastName + '\'' +
                ", candidateTechnicalClearance='" + candidateTechnicalClearance + '\'' +
                ", candidateTechnicalInterviewer='" + candidateTechnicalInterviewer + '\'' +
                ", candidateHrClearance='" + candidateHrClearance + '\'' +
                ", candidateHrInterviewer='" + candidateHrInterviewer + '\'' +
                '}';
    }
}
