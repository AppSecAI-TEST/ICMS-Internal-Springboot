package com.icms.internal.candidate.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CandidateInfo
{
    private int candidateId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String college;
    private String collegeName;
    private String specialization;
    private int gradStartYear;
    private int gradEndYear;
    private int sem1;
    private int sem2;
    private int sem3;
    private int sem4;
    private int sem5;
    private int sem6;
    private int sem7;
    private int sem8;
    private String candidateRegTimeStamp;


    public int getCandidateId ()
    {
        return candidateId;
    }

    public void setCandidateId (final int candidateId)
    {
        this.candidateId = candidateId;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (final String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName ()
    {
        return middleName;
    }

    public void setMiddleName (final String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (final String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (final String gender)
    {
        this.gender = gender;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (final String email)
    {
        this.email = email;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (final String phone)
    {
        this.phone = phone;
    }

    public String getCollege ()
    {
        return college;
    }

    public void setCollege (final String college)
    {
        this.college = college;
    }

    public String getCollegeName ()
    {
        return collegeName;
    }

    public void setCollegeName (final String collegeName)
    {
        this.collegeName = collegeName;
    }

    public String getSpecialization ()
    {
        return specialization;
    }

    public void setSpecialization (final String specialization)
    {
        this.specialization = specialization;
    }

    public int getGradStartYear ()
    {
        return gradStartYear;
    }

    public void setGradStartYear (final int gradStartYear)
    {
        this.gradStartYear = gradStartYear;
    }

    public int getGradEndYear ()
    {
        return gradEndYear;
    }

    public void setGradEndYear (final int gradEndYear)
    {
        this.gradEndYear = gradEndYear;
    }

    public int getSem1 ()
    {
        return sem1;
    }

    public void setSem1 (final int sem1)
    {
        this.sem1 = sem1;
    }

    public int getSem2 ()
    {
        return sem2;
    }

    public void setSem2 (final int sem2)
    {
        this.sem2 = sem2;
    }

    public int getSem3 ()
    {
        return sem3;
    }

    public void setSem3 (final int sem3)
    {
        this.sem3 = sem3;
    }

    public int getSem4 ()
    {
        return sem4;
    }

    public void setSem4 (final int sem4)
    {
        this.sem4 = sem4;
    }

    public int getSem5 ()
    {
        return sem5;
    }

    public void setSem5 (final int sem5)
    {
        this.sem5 = sem5;
    }

    public int getSem6 ()
    {
        return sem6;
    }

    public void setSem6 (final int sem6)
    {
        this.sem6 = sem6;
    }

    public int getSem7 ()
    {
        return sem7;
    }

    public void setSem7 (final int sem7)
    {
        this.sem7 = sem7;
    }

    public int getSem8 ()
    {
        return sem8;
    }

    public void setSem8 (final int sem8)
    {
        this.sem8 = sem8;
    }

    public String getCandidateRegTimeStamp ()
    {
        return candidateRegTimeStamp;
    }

    public void setCandidateRegTimeStamp (final String candidateRegTimeStamp)
    {
        this.candidateRegTimeStamp = candidateRegTimeStamp;
    }

    @Override
    public String toString ()
    {
        return "CandidateInfo{" +
                "candidateId=" + candidateId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", college='" + college + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", gradStartYear=" + gradStartYear +
                ", gradEndYear=" + gradEndYear +
                ", sem1=" + sem1 +
                ", sem2=" + sem2 +
                ", sem3=" + sem3 +
                ", sem4=" + sem4 +
                ", sem5=" + sem5 +
                ", sem6=" + sem6 +
                ", sem7=" + sem7 +
                ", sem8=" + sem8 +
                ", candidateRegTimeStamp='" + candidateRegTimeStamp + '\'' +
                '}';
    }
}
