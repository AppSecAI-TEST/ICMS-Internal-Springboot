package com.icms.internal.college.repository;

import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.college.model.CollegeInfoForm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    @Autowired
    public CollegeRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public boolean addNewCollege(CollegeInfoForm collegeInfoForm) throws SQLException
    {

        String sql = "INSERT INTO CollCollgeInfo (College_Name, College_Address, College_Country, College_City College_PhoneNumber, College_Email, College_TpoName, College_TpoPhoneNumber, College_TpoEmailAddress) values (?,?,?,?,?,?,?,?,?)";

        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1,collegeInfoForm.getCollegeName());
        this.preparedStatement.setString(2,collegeInfoForm.getCollegeAddress());
        this.preparedStatement.setString(3,collegeInfoForm.getCollegeCountry());
        this.preparedStatement.setString(4,collegeInfoForm.getCollegeCity());
        this.preparedStatement.setInt(5,collegeInfoForm.getCollegePhoneNumber());
        this.preparedStatement.setString(6,collegeInfoForm.getCollegeEmail());
        this.preparedStatement.setString(7,collegeInfoForm.getTpoName());
        this.preparedStatement.setInt(8,collegeInfoForm.getTpoPhoneNumber());
        this.preparedStatement.setString(9,collegeInfoForm.getTpoEmail());

        return preparedStatement.executeUpdate() > 0;
    }

    public void add() throws SQLException
    {
//        Connection connection = DbConfig.getInstance();
//        String sql = " insert into CountryCityInfo values ( ? ,? )";
//        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
//
//
//        JSONParser parser = new JSONParser();
//        try
//        {
//            Object object = parser
//                    .parse(new FileReader("E:\\countriesToCities.json"));
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

}

