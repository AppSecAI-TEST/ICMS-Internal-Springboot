package com.icms.internal.interviewer.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.interviewer.model.HrInterviewUpdateForm;
import com.icms.internal.interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.interviewer.model.TechnicalInterviewUpdateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class InterviewRepository
{

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewRepository.class);

    @Autowired
    public InterviewRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }


    public InterviewCandidiateInfo getCandidateInfo (final String candidateRegistrationId) throws Exception
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (InterviewRepository.class)
        {
            if (null == candidateRegistrationId || candidateRegistrationId.length() != 12)
            {
                throw new Exception("Invalid Registration Id");
            }

            String sql = "Select * from InterviewMaster as im left join CandidateMaster as cm on im.Candidate_Id = cm.Candidate_ID left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID left join SpecializationMaster as sm on cm.Candidate_Specialization = sm.Specialization_Id where im.Candidate_Id = ? and SUBSTRING (cm.Candidate_CreatedTimestamp,12,19) = ?";


            String registrationId = candidateRegistrationId.substring(0, 5);
            String timeStamp = candidateRegistrationId.substring(5, 12);

            this.preparedStatement = this.connection.prepareStatement(sql);

            this.preparedStatement.setString(1, registrationId);
            this.preparedStatement.setString(2, timeStamp);

            ResultSet resultSet = this.preparedStatement.executeQuery();

            InterviewCandidiateInfo interviewCandidiateInfo = null;

            while (resultSet.next())
            {
                interviewCandidiateInfo = this.applicationContext.getBean(InterviewCandidiateInfo.class);
                interviewCandidiateInfo.setCandidateId(resultSet.getString("Candidate_ID"));
                interviewCandidiateInfo.setCandidateFirstName(resultSet.getString("Candidate_FirstName"));
                interviewCandidiateInfo.setCandidateMiddlename(resultSet.getString("Candidate_MiddleName"));
                interviewCandidiateInfo.setCandidateLastname(resultSet.getString("Candidate_LastName"));
                interviewCandidiateInfo.setCandidateGender(resultSet.getString("Candidate_Gender"));
                interviewCandidiateInfo.setCandidateCollege(resultSet.getString("College_Name"));
                interviewCandidiateInfo.setCandidateCollegeName(resultSet.getString("Candidate_CollegeName"));
                interviewCandidiateInfo.setCandidateSpecialization(resultSet.getString("Specialization_Value"));

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

                interviewCandidiateInfo.setCandidateHrInterviewStatus(resultSet.getString("Candidate_HrClearance"));

                interviewCandidiateInfo.setCandidateHrInterviewerRemarks(resultSet.getString("Candidate_HrInterviewerRemarks"));
            }
            return interviewCandidiateInfo;
        }
    }

    public boolean updateTechnicalInterviewDetails (final TechnicalInterviewUpdateForm technicalInterviewUpdateForm, final String interviewerName) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        LOGGER.info("Updating candidate Technical Interview status to : "+technicalInterviewUpdateForm + "Interviewer Name : "+ interviewerName);

        synchronized (InterviewRepository.class)
        {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            String sql = "update InterviewMaster set Candidate_TechnicalClearance = ?, Candidate_TechnicalInterviewerRemark = ? ,Candidate_TechnicalInterviewer = ?, Candidate_TechnicalInterviewTimestamp = ?  where Candidate_Id = ? ";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, technicalInterviewUpdateForm.getTechInterviewClearance());
            this.preparedStatement.setString(2, technicalInterviewUpdateForm.getTechInterviewRemarks());
            this.preparedStatement.setString(3, interviewerName.replace("@infocepts.com", ""));
            this.preparedStatement.setString(4, dtf.format(now));
            this.preparedStatement.setString(5, technicalInterviewUpdateForm.getCandidateId());

            if (this.preparedStatement.executeUpdate() > 0)
            {
                LOGGER.info("Technical Interview information updated in DB");
                return true;
            }
            else
            {
                LOGGER.info("Technical Interview information not updated in DB");
                return false;
            }
        }
    }

    public boolean updateHrInterviewDetails (final HrInterviewUpdateForm hrInterviewUpdateForm, final String interviewerName) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        LOGGER.info("Updating candidate HR Interview status to : "+hrInterviewUpdateForm + "Interviewer Name : "+ interviewerName);

        synchronized (InterviewRepository.class)
        {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            String sql = "update InterviewMaster set Candidate_HrClearance = ?, Candidate_HrInterviewerRemarks = ? ,Candidate_HrInterviewer = ? , Candidate_HrInterviewTimestamp = ? where Candidate_Id = ? ";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, hrInterviewUpdateForm.getHrInterviewClearance());
            this.preparedStatement.setString(2, hrInterviewUpdateForm.getHrInterviewRemarks());
            this.preparedStatement.setString(3, interviewerName.replace("@infocepts.com", ""));
            this.preparedStatement.setString(4, dtf.format(now));
            this.preparedStatement.setString(5, hrInterviewUpdateForm.getCandidateId());

            if (this.preparedStatement.executeUpdate() > 0)
            {
                LOGGER.info("HR interview information updated in DB");
                return true;
            }
            else
            {
                LOGGER.info("HR interview information not updated in DB");
                return false;
            }
        }
    }
}
