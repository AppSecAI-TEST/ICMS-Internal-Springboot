package com.icms.internal.registrationsettings.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by matth on 7/1/2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DateRangeForm {
    private String dateRange;

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getStartDate(){
        return this.dateRange.split("-")[0].trim();
    }

    public String getEndDate(){
        return this.dateRange.split("-")[1].trim();
    }
}
