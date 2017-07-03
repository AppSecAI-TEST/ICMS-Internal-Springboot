package com.icms.internal.interviewer.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TechnicalInterviewUpdateForm
{
    private String candidateId;
    private String techInterviewClearance;
    private String techInterviewRemarks;

    public String getTechInterviewClearance ()
    {
        return techInterviewClearance;
    }

    public void setTechInterviewClearance (final String techInterviewClearance)
    {
        this.techInterviewClearance = techInterviewClearance;
    }

    public String getTechInterviewRemarks ()
    {
        return techInterviewRemarks;
    }

    public void setTechInterviewRemarks (final String techInterviewRemarks)
    {
        this.techInterviewRemarks = techInterviewRemarks;
    }

    public String getCandidateId ()
    {
        return candidateId;
    }

    public void setCandidateId (final String candidateId)
    {
        this.candidateId = candidateId;
    }

    @Override
    public String toString ()
    {
        return "TechnicalInterviewUpdateForm{" +
                "candidateId='" + candidateId + '\'' +
                ", techInterviewClearance='" + techInterviewClearance + '\'' +
                ", techInterviewRemarks='" + techInterviewRemarks + '\'' +
                '}';
    }
}
