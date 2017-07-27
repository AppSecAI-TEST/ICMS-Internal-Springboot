package com.icms.internal.college.service;

import com.icms.internal.college.controller.CollegeController;
import com.icms.internal.college.model.CollegeInfo;
import com.icms.internal.college.model.CollegeInfoForm;
import com.icms.internal.college.repository.CollegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class CollegeService
{
    private final CollegeRepository collegeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegeController.class);

    @Autowired
    public CollegeService (final CollegeRepository collegeRepository)
    {
        this.collegeRepository = collegeRepository;
    }

    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.collegeRepository.addNewCollege(collegeInfoForm);
    }

    public List<CollegeInfo> getAllCollegeList() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.collegeRepository.getAllCollegeList();
    }
}
