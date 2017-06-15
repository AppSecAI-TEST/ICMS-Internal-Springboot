package com.icms.internal.college.controller;

import com.icms.internal.college.model.CollegeInfoForm;
import com.icms.internal.college.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/College")
public class CollegeController
{

    private final CollegeService collegeService;

    @Autowired
    public CollegeController (final CollegeService collegeService)
    {
        this.collegeService = collegeService;
    }


    @PostMapping("/Add")
    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {
        return this.collegeService.addNewCollege(collegeInfoForm);
    }

}
