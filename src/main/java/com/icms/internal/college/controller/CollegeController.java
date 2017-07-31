package com.icms.internal.college.controller;

import com.icms.internal.college.model.CollegeInfo;
import com.icms.internal.college.model.CollegeInfoForm;
import com.icms.internal.college.service.CollegeService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/admin/College")
public class CollegeController
{

    private final CollegeService collegeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegeController.class);

    @Autowired
    public CollegeController (final CollegeService collegeService)
    {
        this.collegeService = collegeService;
    }

    @PostMapping("/Add")
    public ResponseEntity<Boolean> addNewCollege(@RequestBody final CollegeInfoForm collegeInfoForm) throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return new ResponseEntity<>(this.collegeService.addNewCollege(collegeInfoForm), HttpStatus.OK);
    }

    @GetMapping("/List")
    public ResponseEntity<List<CollegeInfo>> getAllCollegeList() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.collegeService.getAllCollegeList(), HttpStatus.OK);
    }

}
