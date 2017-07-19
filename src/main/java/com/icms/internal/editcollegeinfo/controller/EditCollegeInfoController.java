package com.icms.internal.editcollegeinfo.controller;

import com.icms.internal.editcollegeinfo.model.CollegeEditForm;
import com.icms.internal.editcollegeinfo.service.EditCollegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/EditCollegeInfo")
public class EditCollegeInfoController {

    private EditCollegeInfoService editCollegeInfoService;

    @Autowired
    public EditCollegeInfoController(EditCollegeInfoService editCollegeInfoService) {
        this.editCollegeInfoService = editCollegeInfoService;
    }

    @PostMapping("/Update")
    public ResponseEntity<?> updateCollegeInfo (@RequestBody CollegeEditForm collegeEditForm) throws SQLException {

        if(this.editCollegeInfoService.updateCollegeInfo(collegeEditForm)) {
            return new ResponseEntity<>( "", HttpStatus.OK);
        } else {
            return new ResponseEntity<>( "", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
