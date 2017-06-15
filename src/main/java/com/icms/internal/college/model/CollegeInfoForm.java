package com.icms.internal.college.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CollegeInfoForm
{
    private String collegeName;
    private String collegeAddress;
    private String collegeCountry;
    private String collegeCity;
    private int collegePhoneNumber;
    private String collegeEmail;
    private String tpoName;
    private int tpoPhoneNumber;
    private String tpoEmail;

    public String getCollegeName ()
    {
        return collegeName;
    }

    public void setCollegeName (final String collegeName)
    {
        this.collegeName = collegeName;
    }

    public String getCollegeAddress ()
    {
        return collegeAddress;
    }

    public void setCollegeAddress (final String collegeAddress)
    {
        this.collegeAddress = collegeAddress;
    }

    public String getCollegeCountry ()
    {
        return collegeCountry;
    }

    public void setCollegeCountry (final String collegeCountry)
    {
        this.collegeCountry = collegeCountry;
    }

    public int getCollegePhoneNumber ()
    {
        return collegePhoneNumber;
    }

    public void setCollegePhoneNumber (final int collegePhoneNumber)
    {
        this.collegePhoneNumber = collegePhoneNumber;
    }

    public String getCollegeEmail ()
    {
        return collegeEmail;
    }

    public void setCollegeEmail (final String collegeEmail)
    {
        this.collegeEmail = collegeEmail;
    }

    public String getTpoName ()
    {
        return tpoName;
    }

    public void setTpoName (final String tpoName)
    {
        this.tpoName = tpoName;
    }

    public int getTpoPhoneNumber ()
    {
        return tpoPhoneNumber;
    }

    public void setTpoPhoneNumber (final int tpoPhoneNumber)
    {
        this.tpoPhoneNumber = tpoPhoneNumber;
    }

    public String getTpoEmail ()
    {
        return tpoEmail;
    }

    public void setTpoEmail (final String tpoEmail)
    {
        this.tpoEmail = tpoEmail;
    }


    public boolean ifFormValid(){
        return true;
    }

    public String getCollegeCity ()
    {
        return collegeCity;
    }

    public void setCollegeCity (final String collegeCity)
    {
        this.collegeCity = collegeCity;
    }

    @Override
    public String toString ()
    {
        return "CollegeInfoForm{" +
                "collegeName='" + collegeName + '\'' +
                ", collegeAddress='" + collegeAddress + '\'' +
                ", collegeCountry='" + collegeCountry + '\'' +
                ", collegeCity='" + collegeCity + '\'' +
                ", collegePhoneNumber=" + collegePhoneNumber +
                ", collegeEmail='" + collegeEmail + '\'' +
                ", tpoName='" + tpoName + '\'' +
                ", tpoPhoneNumber=" + tpoPhoneNumber +
                ", tpoEmail='" + tpoEmail + '\'' +
                '}';
    }
}
