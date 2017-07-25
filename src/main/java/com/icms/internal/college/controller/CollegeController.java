package com.icms.internal.college.controller;

import com.icms.internal.college.model.CollegeInfo;
import com.icms.internal.college.model.CollegeInfoForm;
import com.icms.internal.college.repository.CollegeRepository;
import com.icms.internal.college.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin/College")
public class CollegeController
{

    private final CollegeService collegeService;

    @Autowired
    public CollegeController (final CollegeService collegeService)
    {
        this.collegeService = collegeService;
    }

    @PostMapping("/Add")
    public ResponseEntity<Boolean> addNewCollege(@RequestBody final CollegeInfoForm collegeInfoForm) throws SQLException {
        return new ResponseEntity<>(this.collegeService.addNewCollege(collegeInfoForm), HttpStatus.OK);
    }

    @GetMapping("/List")
    public ResponseEntity<List<CollegeInfo>> getAllCollegeList() throws SQLException {
        return new ResponseEntity<>(this.collegeService.getAllCollegeList(), HttpStatus.OK);
    }

}
