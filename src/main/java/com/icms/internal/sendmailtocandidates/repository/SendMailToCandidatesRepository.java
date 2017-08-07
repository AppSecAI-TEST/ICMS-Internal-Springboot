package com.icms.internal.sendmailtocandidates.repository;


import com.icms.internal.dbconfig.DbConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class SendMailToCandidatesRepository {

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailToCandidatesRepository.class);

    @Autowired
    public SendMailToCandidatesRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public Map<Integer,String> getCollegeNamesFromRegisteredCandidates() throws SQLException {
        String sql = "select DISTINCT (ci.College_Name) from CandidateMaster as cm left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID";

        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        Map<String, String> collegesOfRegisteredCandidate = new LinkedHashMap<>();

        while (resultSet.next()){

            Integer collegeId = resultSet.getInt("Candidate_College");
            String collegeName = resultSet.getString("College_Name");

            if(collegeId == 0){

            }


        }

    }

}
