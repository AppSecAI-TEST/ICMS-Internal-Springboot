package com.icms.internal.candidateinterviewstatus.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.candidateinterviewstatus.model.CandidatesInterviewStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class CandidateInterviewStatusRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateInterviewStatusRepository.class);

    @Autowired
    public CandidateInterviewStatusRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }


    public List<CandidatesInterviewStatus> getInterviewStatusForAll() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "select im.Candidate_ID, cm.Candidate_CreatedTimestamp, cm.Candidate_FirstName, cm.Candidate_LastName, im.Candidate_TechnicalClearance, im.Candidate_TechnicalInterviewer, im.Candidate_HrClearance, im.Candidate_HrInterviewer from InterviewMaster as im left join CandidateMaster as cm on im.Candidate_Id = cm.Candidate_ID";

        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        List<CandidatesInterviewStatus> candidatesInterviewStatusList = new ArrayList<>();

        while (resultSet.next()){

            CandidatesInterviewStatus candidatesInterviewStatus = this.applicationContext.getBean(CandidatesInterviewStatus.class);

            candidatesInterviewStatus.setCandidateId(resultSet.getString("Candidate_ID"));
            candidatesInterviewStatus.setCandidateRegTimeStamp(resultSet.getString("Candidate_CreatedTimestamp"));
            candidatesInterviewStatus.setCandidateName(resultSet.getString("Candidate_FirstName"));
            candidatesInterviewStatus.setCandidateLastName(resultSet.getString("Candidate_LastName"));
            candidatesInterviewStatus.setCandidateTechnicalClearance(resultSet.getString("Candidate_TechnicalClearance"));
            candidatesInterviewStatus.setCandidateTechnicalInterviewer(resultSet.getString("Candidate_TechnicalInterviewer"));
            candidatesInterviewStatus.setCandidateHrClearance(resultSet.getString("Candidate_HrClearance"));
            candidatesInterviewStatus.setCandidateHrInterviewer(resultSet.getString("Candidate_HrInterviewer"));

            candidatesInterviewStatusList.add(candidatesInterviewStatus);
        }

        return candidatesInterviewStatusList;
    }
}
