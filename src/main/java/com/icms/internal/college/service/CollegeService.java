package com.icms.internal.college.service;

import com.icms.internal.college.model.CollegeInfoForm;
import com.icms.internal.college.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class CollegeService
{
    private final CollegeRepository collegeRepository;

    @Autowired
    public CollegeService (final CollegeRepository collegeRepository)
    {
        this.collegeRepository = collegeRepository;
    }

    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {
        return this.collegeRepository.addNewCollege(collegeInfoForm);
    }

}
