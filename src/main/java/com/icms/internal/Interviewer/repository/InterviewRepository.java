package com.icms.internal.Interviewer.repository;

import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.Interviewer.model.InterviewCandidiateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class InterviewRepository
{

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;

    @Autowired
    public InterviewRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }


    public InterviewCandidiateInfo getCandidateInfo(String candidateRegistrationId) throws Exception
    {

        if(null == candidateRegistrationId && candidateRegistrationId.length() != 12){
            throw new Exception("Invalid Registration Id");
        }

        String sql = "select * from CandidateMaster as cm left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID left join SpecializationMaster as sm on cm.Candidate_Specialization = sm.Specialization_Id left join InterviewMaster as im on cm.Candidate_ID = im.candidate_Id where cm.Candidate_ID = ? and SUBSTRING (cm.Candidate_CreatedTimestamp,12,19) = ?";


        String registrationId = candidateRegistrationId.substring(0,5);
        String timeStamp = candidateRegistrationId.substring(5,12);

        this.preparedStatement = this.connection.prepareStatement(sql);

        this.preparedStatement.setString(1,registrationId);
        this.preparedStatement.setString(2,timeStamp);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        InterviewCandidiateInfo interviewCandidiateInfo = this.applicationContext.getBean(InterviewCandidiateInfo.class);

        while (resultSet.next()){
            interviewCandidiateInfo.setCandidateId(resultSet.getString("Candidate_ID"));
            interviewCandidiateInfo.setCandidateFirstName(resultSet.getString("Candidate_FirstName"));
            interviewCandidiateInfo.setCandidateMiddlename(resultSet.getString("Candidate_MiddleName"));
            interviewCandidiateInfo.setCandidateLastname(resultSet.getString("Candidate_LastName"));
            interviewCandidiateInfo.setCandidateGender(resultSet.getString("Candidate_Gender"));
            interviewCandidiateInfo.setCandidateCollege(resultSet.getString("College_Name"));
            interviewCandidiateInfo.setCandidateCollegeName(resultSet.getString("Candidate_CollegeName"));
            interviewCandidiateInfo.setCandidateSpecialization(resultSet.getString( "Specialization_Value"));

            interviewCandidiateInfo.setCandidateGradStartYear(resultSet.getString("Candidate_GradStartYear"));
            interviewCandidiateInfo.setCandidateGradEndYear(resultSet.getString("Candidate_GradEndYear"));
            interviewCandidiateInfo.setCandidateMarksSem1(resultSet.getString("Candidate_Sem1"));
            interviewCandidiateInfo.setCandidateMarksSem2(resultSet.getString("Candidate_Sem2"));
            interviewCandidiateInfo.setCandidateMarksSem3(resultSet.getString("Candidate_Sem3"));
            interviewCandidiateInfo.setCandidateMarksSem4(resultSet.getString("Candidate_Sem4"));
            interviewCandidiateInfo.setCandidateMarksSem5(resultSet.getString("Candidate_Sem5"));
            interviewCandidiateInfo.setCandidateMarksSem6(resultSet.getString("Candidate_Sem6"));
            interviewCandidiateInfo.setCandidateMarksSem7(resultSet.getString("Candidate_Sem7"));
            interviewCandidiateInfo.setCandidateMarksSem8(resultSet.getString("Candidate_Sem8"));

            interviewCandidiateInfo.setCandidateAptitudeMarks(resultSet.getString("Candidate_AptitudeMarks"));

            interviewCandidiateInfo.setTechnicalInterviewerName(resultSet.getString("Candidate_TechnicalInterviewer"));

            interviewCandidiateInfo.setCandidateTechnicalInterviewStatus(resultSet.getString("Candidate_TechnicalClearance"));

            interviewCandidiateInfo.setCandidateTechnicalInterviewerRemarks(resultSet.getString("Candidate_TechnicalInterviewerRemark"));

            interviewCandidiateInfo.setHrInterviewerName(resultSet.getString("Candidate_HrInterviewer"));

            interviewCandidiateInfo.setCandidateHrInterviewStataus(resultSet.getString("Candidate_HrClearance"));

            interviewCandidiateInfo.setCandidateHrInterviewerRemarks(resultSet.getString("Candidate_HrInterviewerRemarks"));
        }

        return interviewCandidiateInfo;
    }
}
