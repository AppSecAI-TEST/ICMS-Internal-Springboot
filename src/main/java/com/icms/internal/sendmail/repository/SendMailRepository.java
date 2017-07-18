package com.icms.internal.sendmail.repository;
import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.sendmail.mailsenderutils.MailSenderUtil;
import com.icms.internal.sendmail.model.SendMailToCollegesAtLocationForm;
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
        String sql = "select Distinct(College_City) from CollegeInfo order by College_City;";
        this.preparedStatement = this.connection.prepareStatement(sql);

        ResultSet resultSet = this.preparedStatement.executeQuery();
        List<String> collegeLocations = new ArrayList<>();

        while (resultSet.next()){
            collegeLocations.add(resultSet.getString("College_City"));
        }

        return collegeLocations;
    }

    public void sendMailToCollegeAtLocation(SendMailToCollegesAtLocationForm sendMailToCollegesAtLocationForm) throws SQLException
    {
        String sql = "select College_Email, College_TpoEmail from CollegeInfo where College_City = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1,sendMailToCollegesAtLocationForm.getCity());

        ResultSet resultSet = this.preparedStatement.executeQuery();

        while (resultSet.next()){
            MailSenderUtil mailSenderUtil = this.applicationContext.getBean(MailSenderUtil.class);
            String college_email = resultSet.getString("College_Email");
            String tpo_email = resultSet.getString("College_TpoEmail");
            String subject = sendMailToCollegesAtLocationForm.getMailSubject();
            String mailBody = sendMailToCollegesAtLocationForm.getMailBody();

            new Thread(() -> mailSenderUtil.sendMailToCollegeAndTpo(college_email, tpo_email, subject , mailBody)).start();

        }
    }


    public void sendMailToColleges(List<String> collegeList){
        
    }


}