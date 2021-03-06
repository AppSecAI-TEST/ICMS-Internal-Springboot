package com.icms.internal.editcollegeinfo.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by matth on 6/18/2017.
 */

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CollegeEditForm {

    private int collegeId;
    private int collegeTier;
    private String collegeAddress;
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
        return "CollegeEditForm{" +
                "collegeId=" + collegeId +
                ", collegeTier=" + collegeTier +
                ", collegeAddress='" + collegeAddress + '\'' +
                ", collegePhoneNumber='" + collegePhoneNumber + '\'' +
                ", collegeEmail='" + collegeEmail + '\'' +
                ", tpoName='" + tpoName + '\'' +
                ", tpoPhoneNumber='" + tpoPhoneNumber + '\'' +
                ", tpoEmail='" + tpoEmail + '\'' +
                '}';
    }
}
