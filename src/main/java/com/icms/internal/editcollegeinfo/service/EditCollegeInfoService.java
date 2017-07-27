package com.icms.internal.editcollegeinfo.service;

import com.icms.internal.editcollegeinfo.model.CollegeEditForm;
import com.icms.internal.editcollegeinfo.repository.EditCollegeInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class EditCollegeInfoService {

    private EditCollegeInfoRepository editCollegeInfoRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EditCollegeInfoService.class);

    @Autowired
    public EditCollegeInfoService(EditCollegeInfoRepository editCollegeInfoRepository) {
        this.editCollegeInfoRepository = editCollegeInfoRepository;
    }


    public Boolean updateCollegeInfo(CollegeEditForm collegeEditForm) throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.editCollegeInfoRepository.updateCollegeInfo(collegeEditForm);
    }
}
