package com.icms.internal.registrationsettings.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by matth on 7/1/2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RegistrationWindowDates {

    private String startDate;
    private String endDate;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
