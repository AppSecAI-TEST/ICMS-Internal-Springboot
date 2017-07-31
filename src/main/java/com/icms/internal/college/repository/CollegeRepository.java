package com.icms.internal.college.repository;

import com.icms.internal.candidate.controller.CandidateController;
import com.icms.internal.candidateinterviewstatus.repository.CandidateInterviewStatusRepository;
import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.college.model.CollegeInfo;
import com.icms.internal.college.model.CollegeInfoForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class CollegeRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegeRepository.class);

    @Autowired
    public CollegeRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (CollegeRepository.class)
        {
            String sql = "INSERT INTO CollegeInfo values (?,?,?,?,?,?,?,?,?,?)";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, collegeInfoForm.getCollegeName());
            this.preparedStatement.setInt(2, collegeInfoForm.getCollegeTier());
            this.preparedStatement.setString(3, collegeInfoForm.getCollegeAddress());
            this.preparedStatement.setString(4, collegeInfoForm.getCollegeCountry());
            this.preparedStatement.setString(5, collegeInfoForm.getCollegeCity());
            this.preparedStatement.setString(6, collegeInfoForm.getCollegePhoneNumber());
            this.preparedStatement.setString(7, collegeInfoForm.getCollegeEmail());
            this.preparedStatement.setString(8, collegeInfoForm.getTpoName());
            this.preparedStatement.setString(9, collegeInfoForm.getTpoPhoneNumber());
            this.preparedStatement.setString(10, collegeInfoForm.getTpoEmail());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public void add() throws SQLException
    {
//        Connection connection = dbconfig.getInstance();
//        String sql = " insert into CountryCityInfo values ( ? ,? )";
//        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
//
//
//        JSONParser parser = new JSONParser();
//        try
//        {
//            Object object = parser
//                    .parse(new FileReader("e:\\countriesToCities.json"));
//
//            //convert Object to JSONObject
//            JSONObject jsonObject = (JSONObject)object;
//
//            //Reading the String
//            Iterator name =  jsonObject.keySet().iterator();
//            List<String> countriesList = new ArrayList<>();
//
//
//            while (name.hasNext())
//            {
//                countriesList.add(name.next().toString());
//            }
//
//            Collections.sort(countriesList);
//
//            for (String country : countriesList){
//                JSONArray cities = (JSONArray) jsonObject.get(country);
//
//                Collections.sort(cities);
//
//                for(Object city : cities){
//
//                    String countrName = new String(country);
//                    String cityName = new String( city.toString() );
//
//
//                    System.out.println(countrName + " \t :"+cityName);
//
//
//                    preparedStatement.setString(1,countrName);
//                    preparedStatement.setString(2,cityName);
//
//                    preparedStatement.executeUpdate();
//
//                }
//            }
//        }
//        catch(FileNotFoundException fe)
//        {
//            fe.printStackTrace();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    public List<CollegeInfo> getAllCollegeList() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (CollegeRepository.class)
        {
            String sql = "select * from CollegeInfo order by College_Name";

            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            List<CollegeInfo> collegeInfoList = new ArrayList<>();

            while (resultSet.next())
            {
                CollegeInfo collegeInfo = this.applicationContext.getBean(CollegeInfo.class);
                collegeInfo.setCollegeId(resultSet.getInt("College_Id"));
                collegeInfo.setCollegeName(resultSet.getString("College_Name"));
                collegeInfo.setCollegeTier(resultSet.getInt("College_tier"));
                collegeInfo.setCollegeAddress(resultSet.getString("College_Address"));
                collegeInfo.setCollegeCountry(resultSet.getString("College_Country"));
                collegeInfo.setCollegeCity(resultSet.getString("College_City"));
                collegeInfo.setCollegePhoneNumber(resultSet.getString("College_PhoneNumber"));
                collegeInfo.setCollegeEmail(resultSet.getString("College_Email"));
                collegeInfo.setTpoName(resultSet.getString("College_TpoName"));
                collegeInfo.setTpoEmail(resultSet.getString("College_TpoEmail"));
                collegeInfo.setTpoPhoneNumber(resultSet.getString("College_TpoPhoneNumber"));

                collegeInfoList.add(collegeInfo);
            }
            return collegeInfoList;
        }
    }
}

