package com.icms.internal.editcollegeinfo.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.editcollegeinfo.model.CollegeEditForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class EditCollegeInfoRepository {

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCollegeInfoRepository.class);

    @Autowired
    public EditCollegeInfoRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }


    public Boolean updateCollegeInfo(CollegeEditForm collegeEditForm) throws SQLException {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "update CollegeInfo set College_Tier = ? , College_Address = ?, College_PhoneNumber = ? , College_Email =? , College_TpoName= ?, College_TpoPhoneNumber= ?, College_TpoEmail = ? where College_ID = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setInt(1, collegeEditForm.getCollegeTier());
        this.preparedStatement.setString(2, collegeEditForm.getCollegeAddress());
        this.preparedStatement.setString(3, collegeEditForm.getCollegePhoneNumber());
        this.preparedStatement.setString(4, collegeEditForm.getCollegeEmail());
        this.preparedStatement.setString(5, collegeEditForm.getTpoName());
        this.preparedStatement.setString(6, collegeEditForm.getTpoPhoneNumber());
        this.preparedStatement.setString(7, collegeEditForm.getTpoEmail());
        this.preparedStatement.setInt(8, collegeEditForm.getCollegeId());

        return this.preparedStatement.executeUpdate() > 0;

    }
}
