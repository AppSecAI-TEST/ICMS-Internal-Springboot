package com.icms.internal.healthstatus.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by matth on 7/15/2017.
 */
@Repository
public class HealthStatusRepository {

    private Connection connection = null;
    private ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthStatusRepository.class);

    @Autowired
    public HealthStatusRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public String getTotalCandidatesRegistered() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (HealthStatusRepository.class)
        {
            String sql = "select count (*) as total_candidates from CandidateMaster";
            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next())
            {
                return resultSet.getString("total_candidates");
            }
            return null;
        }
    }

    public String getTotalCandidatesToInterview() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (HealthStatusRepository.class)
        {
            String sql = "select count (*) as total_candidates_to_interview from InterviewMaster";
            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next())
            {
                return resultSet.getString("total_candidates_to_interview");
            }
            return null;
        }
    }

    public String getTotalCandidatesInterviewed() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (HealthStatusRepository.class)
        {
            String sql = " select count(*) as total_candidates_interviewed from InterviewMaster where Candidate_HrClearance is not null and Candidate_TechnicalClearance is not null;";
            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next())
            {
                return resultSet.getString("total_candidates_interviewed");
            }
            return null;
        }
    }

    public String getTotalCandidatesSelected() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (HealthStatusRepository.class)
        {
            String sql = "select count(*) as total_candidates_selected from InterviewMaster where Candidate_TechnicalClearance = 'Cleared' and Candidate_HrClearance = 'Cleared'";

            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next())
            {
                return resultSet.getString("total_candidates_selected");
            }
            return null;
        }
    }

    public Boolean isDbUp ()
    {
        return this.connection == null ;
    }
}
