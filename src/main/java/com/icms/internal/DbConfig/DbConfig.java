package com.icms.internal.DbConfig;

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

    static
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PreRecruitmentDB", "PreRecDB", "PreRecDB");

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
        if (null != connection)
            return connection;
        throw new SQLException("Database Initialization failed");
    }
}
