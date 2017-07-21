package com.icms.internal.registrationsettings.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.registrationsettings.model.RegistrationWindowDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by matth on 7/1/2017.
 */
@Repository
public class RegistrationSettingsRepository {

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;

    @Autowired
    public RegistrationSettingsRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public RegistrationWindowDates getCurrentRegistrationWindowRange() throws SQLException, ParseException {
        String sql = "select CONVERT(VARCHAR(16),RegistrationWindowStartDate,106) as RegistrationWindowStartDate , CONVERT(VARCHAR(20),RegistrationWindowEndDate,106) as RegistrationWindowEndDate from RegistrationWindowSetting where SettingId = 101";

        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        RegistrationWindowDates registrationWindowDates = this.applicationContext.getBean(RegistrationWindowDates.class);
        if(resultSet.next()){
            registrationWindowDates.setStartDate(resultSet.getString("RegistrationWindowStartDate"));
            registrationWindowDates.setEndDate(resultSet.getString("RegistrationWindowEndDate"));
        }


        return registrationWindowDates;

    }


    public boolean saveRegistrationWindowDates( final String startDate, final String endDate ) throws SQLException, ParseException {
        //System.out.println(startDate + "-" + endDate);

        String sql = "update RegistrationWindowSetting set RegistrationWindowStartDate = ?,  RegistrationWindowEndDate= ? where SettingId = 101 ";

        Date startDt =  new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(startDate).getTime());
        Date endDt =   new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(endDate).getTime());

        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setDate(1,startDt);
        this.preparedStatement.setDate(2,endDt);

        return   this.preparedStatement.executeUpdate() == 1;
    }


    private void backupCandidateMaster()throws SQLException {

        String sql = "insert into OldRegistrationCandidateMaster select * from CandidateMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.execute();
    }

    private boolean clearCandidateMaster() throws SQLException {


        //Clear records from Candidate master
        String sql = "delete from CandidateMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.execute();


        //Clear records from Interview Master
        sql ="DELETE  from InterviewMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.execute();


        sql = "select IDENT_CURRENT('CandidateMaster') as pkValue";
        this.preparedStatement = this.connection.prepareStatement(sql);


        if(this.currentPkValueInCandidateMaster() > 88888)
        {
            //Reset the CandidateRegId (Pk) to 10001
            sql = "DBCC CHECKIDENT ('CandidateMaster', RESEED, 10000)";
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.execute();
        }

        //check if there are no rows in the candidateMaster table DB
        sql = "select count (*) as Candidate_Records from CandidateMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();
        if(resultSet.next())
        {
            int rows = resultSet.getInt("Candidate_Records");
            return rows == 0;
        }

        return false;
    }


    private int currentPkValueInCandidateMaster() throws SQLException
    {
        String sql = "select IDENT_CURRENT('CandidateMaster') as pkValue";

        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("pkValue");
        }

        return  0;
    }

    public boolean dataBaseCleanUp() throws SQLException
    {
        this.backupCandidateMaster();
        return this.clearCandidateMaster();
    }

}
