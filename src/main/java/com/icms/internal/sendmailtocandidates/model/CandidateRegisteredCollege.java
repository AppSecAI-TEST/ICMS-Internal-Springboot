package com.icms.internal.sendmailtocandidates.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CandidateRegisteredCollege
{
    private Integer collegeId;
    private String collegeName;

    public Integer getCollegeId ()
    {
        return collegeId;
    }

    public void setCollegeId (final Integer collegeId)
    {
        this.collegeId = collegeId;
    }

    public String getCollegeName ()
    {
        return collegeName;
    }

    public void setCollegeName (final String collegeName)
    {
        this.collegeName = collegeName;
    }

    @Override
    public String toString ()
    {
        return "CandidateRegisteredCollege{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}
