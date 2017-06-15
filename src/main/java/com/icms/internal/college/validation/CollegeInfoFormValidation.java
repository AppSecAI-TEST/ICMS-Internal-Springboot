package com.icms.internal.college.validation;

import com.icms.internal.college.model.CollegeInfoForm;

/**
 * Created by Infocepts India in 2017.
 */
public class CollegeInfoFormValidation
{
    private CollegeInfoForm collegeInfoForm;

    public CollegeInfoFormValidation (final CollegeInfoForm collegeInfoForm)
    {
        this.collegeInfoForm = collegeInfoForm;
    }

    public boolean isFormValid(){
        return true;
    }

}
