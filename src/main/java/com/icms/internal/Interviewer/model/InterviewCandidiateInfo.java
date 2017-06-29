package com.icms.internal.Interviewer.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InterviewCandidiateInfo
{
    private String candidateId;
    private String candidateFirstName;
    private String candidateMiddlename;
    private String candidateLastname;
    private String candidateGender;
    private String candidateCollege;
    private String candidateCollegeName;
    private String candidateSpecialization;
    private String candidateGradStartYear;
    private String candidateGradEndYear;
    private String candidateMarksSem1;
    private String candidateMarksSem2;
    private String candidateMarksSem3;
    private String candidateMarksSem4;
    private String candidateMarksSem5;
    private String candidateMarksSem6;
    private String candidateMarksSem7;
    private String candidateMarksSem8;
    private String candidateAptitudeMarks;
    private String technicalInterviewerName;
    private String candidateTechnicalInterviewStatus;
    private String candidateTechnicalInterviewerRemarks;
    private String hrInterviewerName;
    private String candidateHrInterviewStatus;
    private String candidateHrInterviewerRemarks;

    public String getCandidateId ()
    {
        return candidateId;
    }

    public void setCandidateId (final String candidateId)
    {
        this.candidateId = candidateId;
    }

    public String getCandidateFirstName ()
    {
        return candidateFirstName;
    }

    public void setCandidateFirstName (final String candidateFirstName)
    {
        this.candidateFirstName = candidateFirstName;
    }

    public String getCandidateMiddlename ()
    {
        return candidateMiddlename;
    }

    public void setCandidateMiddlename (final String candidateMiddlename)
    {
        this.candidateMiddlename = candidateMiddlename;
    }

    public String getCandidateLastname ()
    {
        return candidateLastname;
    }

    public void setCandidateLastname (final String candidateLastname)
    {
        this.candidateLastname = candidateLastname;
    }

    public String getCandidateGender ()
    {
        return candidateGender;
    }

    public void setCandidateGender (final String candidateGender)
    {
        this.candidateGender = candidateGender;
    }

    public String getCandidateCollege ()
    {
        return candidateCollege;
    }

    public void setCandidateCollege (final String candidateCollege)
    {
        this.candidateCollege = candidateCollege;
    }

    public String getCandidateCollegeName ()
    {
        return candidateCollegeName;
    }

    public void setCandidateCollegeName (final String candidateCollegeName)
    {
        this.candidateCollegeName = candidateCollegeName;
    }

    public String getCandidateSpecialization ()
    {
        return candidateSpecialization;
    }

    public void setCandidateSpecialization (final String candidateSpecialization)
    {
        this.candidateSpecialization = candidateSpecialization;
    }

    public String getCandidateGradStartYear ()
    {
        return candidateGradStartYear;
    }

    public void setCandidateGradStartYear (final String candidateGradStartYear)
    {
        this.candidateGradStartYear = candidateGradStartYear;
    }

    public String getCandidateGradEndYear ()
    {
        return candidateGradEndYear;
    }

    public void setCandidateGradEndYear (final String candidateGradEndYear)
    {
        this.candidateGradEndYear = candidateGradEndYear;
    }

    public String getCandidateMarksSem1 ()
    {
        return candidateMarksSem1;
    }

    public void setCandidateMarksSem1 (final String candidateMarksSem1)
    {
        this.candidateMarksSem1 = candidateMarksSem1;
    }

    public String getCandidateMarksSem2 ()
    {
        return candidateMarksSem2;
    }

    public void setCandidateMarksSem2 (final String candidateMarksSem2)
    {
        this.candidateMarksSem2 = candidateMarksSem2;
    }

    public String getCandidateMarksSem3 ()
    {
        return candidateMarksSem3;
    }

    public void setCandidateMarksSem3 (final String candidateMarksSem3)
    {
        this.candidateMarksSem3 = candidateMarksSem3;
    }

    public String getCandidateMarksSem4 ()
    {
        return candidateMarksSem4;
    }

    public void setCandidateMarksSem4 (final String candidateMarksSem4)
    {
        this.candidateMarksSem4 = candidateMarksSem4;
    }

    public String getCandidateMarksSem5 ()
    {
        return candidateMarksSem5;
    }

    public void setCandidateMarksSem5 (final String candidateMarksSem5)
    {
        this.candidateMarksSem5 = candidateMarksSem5;
    }

    public String getCandidateMarksSem6 ()
    {
        return candidateMarksSem6;
    }

    public void setCandidateMarksSem6 (final String candidateMarksSem6)
    {
        this.candidateMarksSem6 = candidateMarksSem6;
    }

    public String getCandidateMarksSem7 ()
    {
        return candidateMarksSem7;
    }

    public void setCandidateMarksSem7 (final String candidateMarksSem7)
    {
        this.candidateMarksSem7 = candidateMarksSem7;
    }

    public String getCandidateMarksSem8 ()
    {
        return candidateMarksSem8;
    }

    public void setCandidateMarksSem8 (final String candidateMarksSem8)
    {
        this.candidateMarksSem8 = candidateMarksSem8;
    }

    public String getCandidateAptitudeMarks ()
    {
        return candidateAptitudeMarks;
    }

    public void setCandidateAptitudeMarks (final String candidateAptitudeMarks)
    {
        this.candidateAptitudeMarks = candidateAptitudeMarks;
    }

    public String getTechnicalInterviewerName ()
    {
        return technicalInterviewerName;
    }

    public void setTechnicalInterviewerName (final String technicalInterviewerName)
    {
        this.technicalInterviewerName = technicalInterviewerName;
    }

    public String getCandidateTechnicalInterviewStatus ()
    {
        return candidateTechnicalInterviewStatus;
    }

    public void setCandidateTechnicalInterviewStatus (final String candidateTechnicalInterviewStatus)
    {
        this.candidateTechnicalInterviewStatus = candidateTechnicalInterviewStatus;
    }

    public String getCandidateTechnicalInterviewerRemarks ()
    {
        return candidateTechnicalInterviewerRemarks;
    }

    public void setCandidateTechnicalInterviewerRemarks (final String candidateTechnicalInterviewerRemarks)
    {
        this.candidateTechnicalInterviewerRemarks = candidateTechnicalInterviewerRemarks;
    }

    public String getHrInterviewerName ()
    {
        return hrInterviewerName;
    }

    public void setHrInterviewerName (final String hrInterviewerName)
    {
        this.hrInterviewerName = hrInterviewerName;
    }

    public String getCandidateHrInterviewStatus ()
    {
        return candidateHrInterviewStatus;
    }

    public void setCandidateHrInterviewStatus (final String candidateHrInterviewStatus)
    {
        this.candidateHrInterviewStatus = candidateHrInterviewStatus;
    }

    public String getCandidateHrInterviewerRemarks ()
    {
        return candidateHrInterviewerRemarks;
    }

    public void setCandidateHrInterviewerRemarks (final String candidateHrInterviewerRemarks)
    {
        this.candidateHrInterviewerRemarks = candidateHrInterviewerRemarks;
    }

    @Override
    public String toString ()
    {
        return "InterviewCandidiateInfo{" +
                "candidateId='" + candidateId + '\'' +
                ", candidateFirstName='" + candidateFirstName + '\'' +
                ", candidateMiddlename='" + candidateMiddlename + '\'' +
                ", candidateLastname='" + candidateLastname + '\'' +
                ", candidateGender='" + candidateGender + '\'' +
                ", candidateCollege='" + candidateCollege + '\'' +
                ", candidateCollegeName='" + candidateCollegeName + '\'' +
                ", candidateSpecialization='" + candidateSpecialization + '\'' +
                ", candidateGradStartYear='" + candidateGradStartYear + '\'' +
                ", candidateGradEndYear='" + candidateGradEndYear + '\'' +
                ", candidateMarksSem1='" + candidateMarksSem1 + '\'' +
                ", candidateMarksSem2='" + candidateMarksSem2 + '\'' +
                ", candidateMarksSem3='" + candidateMarksSem3 + '\'' +
                ", candidateMarksSem4='" + candidateMarksSem4 + '\'' +
                ", candidateMarksSem5='" + candidateMarksSem5 + '\'' +
                ", candidateMarksSem6='" + candidateMarksSem6 + '\'' +
                ", candidateMarksSem7='" + candidateMarksSem7 + '\'' +
                ", candidateMarksSem8='" + candidateMarksSem8 + '\'' +
                ", candidateAptitudeMarks='" + candidateAptitudeMarks + '\'' +
                ", technicalInterviewerName='" + technicalInterviewerName + '\'' +
                ", candidateTechnicalInterviewStatus='" + candidateTechnicalInterviewStatus + '\'' +
                ", candidateTechnicalInterviewerRemarks='" + candidateTechnicalInterviewerRemarks + '\'' +
                ", hrInterviewerName='" + hrInterviewerName + '\'' +
                ", candidateHrInterviewStatus='" + candidateHrInterviewStatus + '\'' +
                ", candidateHrInterviewerRemarks='" + candidateHrInterviewerRemarks + '\'' +
                '}';
    }
}
