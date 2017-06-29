package com.icms.internal.Interviewer.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HrInterviewUpdateForm
{
    private String candidateId;
    private String hrInterviewClearance;
    private String hrInterviewRemarks;


    public String getCandidateId ()
    {
        return candidateId;
    }

    public void setCandidateId (final String candidateId)
    {
        this.candidateId = candidateId;
    }

    public String getHrInterviewClearance ()
    {
        return hrInterviewClearance;
    }

    public void setHrInterviewClearance (final String hrInterviewClearance)
    {
        this.hrInterviewClearance = hrInterviewClearance;
    }

    public String getHrInterviewRemarks ()
    {
        return hrInterviewRemarks;
    }

    public void setHrInterviewRemarks (final String hrInterviewRemarks)
    {
        this.hrInterviewRemarks = hrInterviewRemarks;
    }

    @Override
    public String toString ()
    {
        return "HrInterviewUpdateForm{" +
                "candidateId='" + candidateId + '\'' +
                ", hrInterviewClearance='" + hrInterviewClearance + '\'' +
                ", hrInterviewRemarks='" + hrInterviewRemarks + '\'' +
                '}';
    }
}
