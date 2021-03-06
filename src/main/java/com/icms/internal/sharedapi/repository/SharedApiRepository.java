package com.icms.internal.sharedapi.repository;

import com.icms.internal.dbconfig.DbConfig;
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
public class SharedApiRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(SharedApiRepository.class);

    @Autowired
    public SharedApiRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public List<String> getCountryList () throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = "select Distinct(Country_Name) from CountryCityInfo order by Country_Name";

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<String> countryList = new ArrayList<>();

        while (resultSet.next())
        {
            countryList.add(resultSet.getString("Country_Name"));
        }


        return countryList;
    }

    public List<String> getCityList (String countryName) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String sql = String.format("select City_Name from CountryCityInfo where Country_Name = '%s' order by City_Name", countryName);

        Statement statement = this.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        List<String> cityList = new ArrayList<>();

        while (resultSet.next())
        {
            cityList.add(resultSet.getString("City_Name"));
        }

        return cityList;
    }
}
