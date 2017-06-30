package com.icms.internal.DbConfig;

import com.icms.internal.Interviewer.contoller.InterviewContoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DbConfig
{
    private static Connection connection = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewContoller.class);

    static
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=RecruitmentDB", "PreRecDB", "PreRecDB");

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    private DbConfig ()
    {
    }

    public static Connection getInstance () throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        if (null != connection)
            return connection;
        throw new SQLException("Database Initialization failed");
    }
}
