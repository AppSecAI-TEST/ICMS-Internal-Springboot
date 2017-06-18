package com.icms.internal.college.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by matth on 6/18/2017.
 */

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CollegeInfo {

    private int collegeId;
    private String collegeName;
    private int collegeTier;
    private String collegeAddress;
    private String collegeCountry;
    private String collegeCity;
    private String collegePhoneNumber;
    private String collegeEmail;
    private String tpoName;
    private String tpoPhoneNumber;
    private String tpoEmail;

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getCollegeTier() {
        return collegeTier;
    }

    public void setCollegeTier(int collegeTier) {
        this.collegeTier = collegeTier;
    }

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public String getCollegeCountry() {
        return collegeCountry;
    }

    public void setCollegeCountry(String collegeCountry) {
        this.collegeCountry = collegeCountry;
    }

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public String getCollegePhoneNumber() {
        return collegePhoneNumber;
    }

    public void setCollegePhoneNumber(String collegePhoneNumber) {
        this.collegePhoneNumber = collegePhoneNumber;
    }

    public String getCollegeEmail() {
        return collegeEmail;
    }

    public void setCollegeEmail(String collegeEmail) {
        this.collegeEmail = collegeEmail;
    }

    public String getTpoName() {
        return tpoName;
    }

    public void setTpoName(String tpoName) {
        this.tpoName = tpoName;
    }

    public String getTpoPhoneNumber() {
        return tpoPhoneNumber;
    }

    public void setTpoPhoneNumber(String tpoPhoneNumber) {
        this.tpoPhoneNumber = tpoPhoneNumber;
    }

    public String getTpoEmail() {
        return tpoEmail;
    }

    public void setTpoEmail(String tpoEmail) {
        this.tpoEmail = tpoEmail;
    }

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", collegeTier=" + collegeTier +
                ", collegeAddress='" + collegeAddress + '\'' +
                ", collegeCountry='" + collegeCountry + '\'' +
                ", collegeCity='" + collegeCity + '\'' +
                ", collegePhoneNumber='" + collegePhoneNumber + '\'' +
                ", collegeEmail='" + collegeEmail + '\'' +
                ", tpoName='" + tpoName + '\'' +
                ", tpoPhoneNumber='" + tpoPhoneNumber + '\'' +
                ", tpoEmail='" + tpoEmail + '\'' +
                '}';
    }
}
