package com.icms.internal.editcollegeinfo.service;

import com.icms.internal.editcollegeinfo.model.CollegeEditForm;
import com.icms.internal.editcollegeinfo.repository.EditCollegeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class EditCollegeInfoService {

    private EditCollegeInfoRepository editCollegeInfoRepository;

    @Autowired
    public EditCollegeInfoService(EditCollegeInfoRepository editCollegeInfoRepository) {
        this.editCollegeInfoRepository = editCollegeInfoRepository;
    }


    public Boolean updateCollegeInfo(CollegeEditForm collegeEditForm) throws SQLException {
        return this.editCollegeInfoRepository.updateCollegeInfo(collegeEditForm);
    }
}
