package com.icms.internal.login.repository;

import com.icms.internal.DbConfig.DbConfig;
import com.icms.internal.login.controller.LoginController;
import com.icms.internal.login.models.LoginForm;
import com.icms.internal.login.models.LoginResponse;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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

        LOGGER.debug(">> " + new Object(){}.getClass().getEnclosingMethod().getName());

        // String sql = "select * from LoginInfo where Login_name = ? and Login_Password = ?";
        String sql = "select * from LoginInfo where Login_name = ?";

        this.preparedStatement = this.connection.prepareStatement(sql);

        preparedStatement.setString(1, loginForm.getUsername().replace("@infocepts.com",""));
        //preparedStatement.setString(2, loginForm.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();
        LoginResponse loginResponse = this.applicationContext.getBean(LoginResponse.class);



        if (resultSet.next() && this.doLdapLogin(loginForm))
        {
            String usernamePassword = loginForm.getUsername() + ":" + loginForm.getPassword();
            String token = Base64Utils.encodeToString(usernamePassword.getBytes());
            loginResponse.setAuthToken(token);
            loginResponse.setUsername(resultSet.getString("Login_Name"));
            loginResponse.setAuthRole(resultSet.getString("Role").trim());
        }
        else
        {
            loginResponse.setErrorMessage("Invalid Username Password.");
        }

        return loginResponse;
    }

    public boolean doLdapLogin(final LoginForm loginForm){
        try
        {

            String uname = loginForm.getUsername();

            if( ! loginForm.getUsername().toLowerCase().contains("@infocepts.com"))
                 uname += "@infocepts.com";

            LDAPConnection ldapConnection = new LDAPConnection("info-srv11.infocepts.com", 389, uname, loginForm.getPassword());

            if(ldapConnection.isConnected()){
                return true;
            } else {
                return false;
            }
        }
        catch (LDAPException e)
        {
            //System.out.println(e.getMessage());
            //e.printStackTrace();
        }

        return false;
    }
}
