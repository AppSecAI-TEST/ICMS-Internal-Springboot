package com.icms.internal.login.repository;

import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class LoginRepository
{

    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;

    @Autowired
    public LoginRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }


    public LoginResponse doLogin (final LoginForm loginForm) throws SQLException
    {
        String sql = "select * from LoginInfo where Login_name = ? and Login_Password = ?";

        this.preparedStatement = this.connection.prepareStatement(sql);

        preparedStatement.setString(1,loginForm.getUsername());
        preparedStatement.setString(2,loginForm.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();
        LoginResponse loginResponse = this.applicationContext.getBean(LoginResponse.class);

        if(resultSet.next()){

            String usernamePassword = loginForm.getUsername() + ":" + loginForm.getPassword();
            String token = Base64Utils.encodeToString(usernamePassword.getBytes());
            loginResponse.setAuthToken(token);
            loginResponse.setUsername(resultSet.getString("Login_Name"));
            loginResponse.setAuthRole(resultSet.getString("Role").trim());

        }else{
            loginResponse.setErrorMessage("Invalid Username Password.");
        }

        return loginResponse;
    }
}
