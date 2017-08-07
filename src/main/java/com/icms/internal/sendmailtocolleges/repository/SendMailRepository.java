package com.icms.internal.sendmailtocolleges.repository;
import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.sendmailtocolleges.mailsenderutils.MailSenderUtil;
import com.icms.internal.sendmailtocolleges.model.SendMailToCollegesAtLocationForm;
import com.icms.internal.sendmailtocolleges.model.SendMailToCollegesForm;
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
public class SendMailRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailRepository.class);

    @Autowired
    public SendMailRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public List<String> getCollegeCities() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (SendMailRepository.class)
        {

            String sql = "select Distinct(College_City) from CollegeInfo order by College_City;";
            this.preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<String> collegeLocations = new ArrayList<>();

            while (resultSet.next())
            {
                collegeLocations.add(resultSet.getString("College_City"));
            }

            return collegeLocations;
        }
    }

    public List<String> getCollegeNames() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

            String sql = "select College_name from CollegeInfo order by College_Name";
            this.preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            List collegeList = new ArrayList();

            while (resultSet.next())
            {
                String collegeName = resultSet.getString("College_Name");
                collegeList.add(collegeName);
            }

            return collegeList;

    }

    public void sendMailToCollegeAtLocation(SendMailToCollegesAtLocationForm sendMailToCollegesAtLocationForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "select College_Email, College_TpoEmail from CollegeInfo where College_City = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1,sendMailToCollegesAtLocationForm.getCity());

        ResultSet resultSet = this.preparedStatement.executeQuery();

        while (resultSet.next()){
            MailSenderUtil mailSenderUtil = this.applicationContext.getBean(MailSenderUtil.class);
            String collegeEmail = resultSet.getString("College_Email");
            String tpoEmail = resultSet.getString("College_TpoEmail");
            String subject = sendMailToCollegesAtLocationForm.getMailSubject();
            String mailBody = sendMailToCollegesAtLocationForm.getMailBody();

            new Thread(() -> mailSenderUtil.sendMailToCollegeAndTpo(collegeEmail, tpoEmail, subject , mailBody)).start();

        }
    }


    public void sendMailToColleges(SendMailToCollegesForm sendMailToCollegesForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        List<String> collegeIdList = sendMailToCollegesForm.getColleges();

        for(String collegeId : collegeIdList ){

            String sql = "  select College_Email, College_TpoEmail from CollegeInfo where College_ID = ?";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1,collegeId);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            if(resultSet.next()){
                String collegeEmail = resultSet.getString("College_Email");
                String tpoEmail = resultSet.getString("College_TpoEmail");
                String subject = sendMailToCollegesForm.getMailSubject();
                String mailBody = sendMailToCollegesForm.getMailBody();

                MailSenderUtil mailSenderUtil = this.applicationContext.getBean(MailSenderUtil.class);

                new Thread(() -> mailSenderUtil.sendMailToCollegeAndTpo(collegeEmail, tpoEmail, subject , mailBody)).start();

            }

        }
        
    }


}
