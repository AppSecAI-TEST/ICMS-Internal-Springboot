package com.icms.internal.candidate.repopsitory;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.candidate.model.CandidateInfo;
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
public class CandidateRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateRepository.class);

    @Autowired
    public CandidateRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public List<CandidateInfo> getAllCandidateList () throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        String sql = "select * from CandidateMaster as cm left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID left join SpecializationMaster as sm on cm.Candidate_Specialization = sm.Specialization_Id";

        this.preparedStatement = this.connection.prepareStatement(sql);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        List<CandidateInfo> candidateInfoList = new ArrayList<>();

        while (resultSet.next()){
            CandidateInfo candidateInfo = this.applicationContext.getBean(CandidateInfo.class);
            candidateInfo.setCandidateId(resultSet.getInt("Candidate_ID"));
            candidateInfo.setFirstName(resultSet.getString("Candidate_FirstName"));
            candidateInfo.setMiddleName(resultSet.getString("Candidate_MiddleName"));
            candidateInfo.setLastName(resultSet.getString("Candidate_LastName"));
            candidateInfo.setGender(resultSet.getString("Candidate_Gender"));
            candidateInfo.setEmail(resultSet.getString("Candidate_Email"));
            candidateInfo.setPhone(resultSet.getString("Candidate_Phone"));
            candidateInfo.setCollege(resultSet.getString("College_Name"));
            candidateInfo.setCollegeName(resultSet.getString("Candidate_CollegeName"));
            candidateInfo.setSpecialization(resultSet.getString("Specialization_Value"));
            candidateInfo.setGradStartYear(resultSet.getInt("Candidate_GradStartYear"));
            candidateInfo.setGradEndYear(resultSet.getInt("Candidate_GradEndYear"));
            candidateInfo.setSem1(resultSet.getInt("Candidate_Sem1"));
            candidateInfo.setSem2(resultSet.getInt("Candidate_Sem2"));
            candidateInfo.setSem3(resultSet.getInt("Candidate_Sem3"));
            candidateInfo.setSem4(resultSet.getInt("Candidate_Sem4"));
            candidateInfo.setSem5(resultSet.getInt("Candidate_Sem5"));
            candidateInfo.setSem6(resultSet.getInt("Candidate_Sem6"));
            candidateInfo.setSem7(resultSet.getInt("Candidate_Sem7"));
            candidateInfo.setSem8(resultSet.getInt("Candidate_Sem8"));
            candidateInfo.setCandidateRegTimeStamp(resultSet.getString("Candidate_CreatedTimestamp"));

            candidateInfoList.add(candidateInfo);
        }

        return candidateInfoList;
    }
}
