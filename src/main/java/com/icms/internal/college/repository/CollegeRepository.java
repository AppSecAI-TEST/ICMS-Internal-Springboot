package com.icms.internal.college.repository;

import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.college.model.CollegeInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class CollegeRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    @Autowired
    public CollegeRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {

        String sql = "INSERT INTO CollCollgeInfo (College_Name, College_Address, College_Country, College_City College_PhoneNumber, College_Email, College_TpoName, College_TpoPhoneNumber, College_TpoEmailAddress) values (?,?,?,?,?,?,?,?,?)";

        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1,collegeInfoForm.getCollegeName());
        this.preparedStatement.setString(2,collegeInfoForm.getCollegeAddress());
        this.preparedStatement.setString(3,collegeInfoForm.getCollegeCountry());
        this.preparedStatement.setString(4,collegeInfoForm.getCollegeCity());
        this.preparedStatement.setInt(5,collegeInfoForm.getCollegePhoneNumber());
        this.preparedStatement.setString(6,collegeInfoForm.getCollegeEmail());
        this.preparedStatement.setString(7,collegeInfoForm.getTpoName());
        this.preparedStatement.setInt(8,collegeInfoForm.getTpoPhoneNumber());
        this.preparedStatement.setString(9,collegeInfoForm.getTpoEmail());

        return preparedStatement.executeUpdate() > 0;
    }


}
