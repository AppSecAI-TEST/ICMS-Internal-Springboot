package com.icms.internal.sendmailtocandidates.repository;


import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.mailsenderutils.MailSenderUtil;
import com.icms.internal.sendmailtocandidates.model.CandidateRegisteredCollege;
import com.icms.internal.sendmailtocandidates.model.MailToAllRegisteredCandidates;
import com.icms.internal.sendmailtocandidates.model.MailToCandidatesFromCollegeForm;
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


    public List<CandidateRegisteredCollege> getCollegeNamesFromRegisteredCandidates() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "select DISTINCT (ci.College_Name), cm.Candidate_College from CandidateMaster as cm left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID";

        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();

        List<CandidateRegisteredCollege> collegesOfRegisteredCandidate = new ArrayList<>();

        while (resultSet.next()){

            Integer collegeId = resultSet.getInt("Candidate_College");
            String collegeName = resultSet.getString("College_Name");

            CandidateRegisteredCollege candidateRegisteredCollege = this.applicationContext.getBean(CandidateRegisteredCollege.class);
            candidateRegisteredCollege.setCollegeId(collegeId);

            if(collegeId == 0){
                candidateRegisteredCollege.setCollegeName("Other Colleges");
            }
            else{
                candidateRegisteredCollege.setCollegeName(collegeName);
            }
            collegesOfRegisteredCandidate.add(candidateRegisteredCollege);
        }
        return collegesOfRegisteredCandidate;
    }

    public void sendMailToCandidatesFromCollege(MailToCandidatesFromCollegeForm mailToCandidatesFromCollegeForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        MailSenderUtil mailSenderUtil = this.applicationContext.getBean(MailSenderUtil.class);

        List<String> emailIds = this.getMailIdsForCollege(mailToCandidatesFromCollegeForm.getCollegeId());

        new Thread(() -> mailSenderUtil.sendMailToCandidates( emailIds, mailToCandidatesFromCollegeForm.getMailSubject(), mailToCandidatesFromCollegeForm.getMailBody())
        ).start();
    }

    public void sendMailToAllRegisteredCandidates(MailToAllRegisteredCandidates mailToAllRegisteredCandidates) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        MailSenderUtil mailSenderUtil = this.applicationContext.getBean(MailSenderUtil.class);

        List<String> emailIds = this.getAllRegisteredMailIds();

        new Thread(() -> mailSenderUtil.sendMailToCandidates( emailIds, mailToAllRegisteredCandidates.getMailSubject(), mailToAllRegisteredCandidates.getMailBody())
        ).start();
    }

    private List<String> getMailIdsForCollege(String collegeId) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "Select cm.Candidate_Email from CandidateMaster as cm where cm.Candidate_College = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1,collegeId);

        ResultSet resultSet = this.preparedStatement.executeQuery();
        List<String> emailIds = new ArrayList<>();

        while (resultSet.next()){
            emailIds.add(resultSet.getString("Candidate_Email"));
        }

        return emailIds;
    }

    private List<String> getAllRegisteredMailIds() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "Select Candidate_Email from CandidateMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();
        List<String> emailIds = new ArrayList<>();

        while (resultSet.next()){
            emailIds.add(resultSet.getString("Candidate_Email"));
        }

        return emailIds;
    }

}
