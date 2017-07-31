package com.icms.internal.usermanagement.repository;

import com.icms.internal.dbconfig.DbConfig;
import com.icms.internal.registrationsettings.repository.RegistrationSettingsRepository;
import com.icms.internal.usermanagement.model.AddUserForm;
import com.icms.internal.usermanagement.model.ROLE;
import com.icms.internal.usermanagement.model.UserInfoAndRole;
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
public class UserManagementRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementRepository.class);

    @Autowired
    public UserManagementRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public List<UserInfoAndRole> getAllUsers() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        synchronized (UserManagementRepository.class)
        {
            String sql = "select Login_name, Role from LoginInfo where Login_name != 'Admin'";
            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            List<UserInfoAndRole> userInfoAndRoleList = new ArrayList<>();

            while (resultSet.next())
            {
                UserInfoAndRole userInfoAndRole = this.applicationContext.getBean(UserInfoAndRole.class);

                userInfoAndRole.setUsername(resultSet.getString("Login_name"));
                String role = resultSet.getString("Role");

                if (role.equalsIgnoreCase("A"))
                {
                    userInfoAndRole.setRole(ROLE.Admin);
                }
                else if (role.equalsIgnoreCase("H"))
                {
                    userInfoAndRole.setRole(ROLE.HR);
                }
                else
                {
                    userInfoAndRole.setRole(ROLE.INTERVIEWER);
                }

                userInfoAndRoleList.add(userInfoAndRole);
            }
            return userInfoAndRoleList;
        }

    }

    public boolean addNewUser(final AddUserForm addUserForm) throws SQLException
    {

        LOGGER.debug(">> " + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        synchronized (UserManagementRepository.class)
        {

            for (UserInfoAndRole userInfoAndRole : this.getAllUsers())
            {
                if (userInfoAndRole.getUsername().equalsIgnoreCase(addUserForm.getUserEmail()))
                {
                    throw new SQLException("User Already Exists.");
                }
            }

            String sql = "insert into LoginInfo (Login_name, Role) values (?,?)";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, addUserForm.getUserEmail());

            if (addUserForm.getRole().equalsIgnoreCase("A"))
            {
                this.preparedStatement.setString(2, "A");
            }
            else if (addUserForm.getRole().equalsIgnoreCase("H"))
            {
                this.preparedStatement.setString(2, "H");
            }
            else
            {
                this.preparedStatement.setString(2, "I");
            }

            return this.preparedStatement.executeUpdate() > 0;
        }
    }


    public boolean deleteUser(final String username) throws SQLException
    {

        LOGGER.debug(">> " + new Object()
        {
        }.getClass().getEnclosingMethod().getName());

        synchronized (UserManagementRepository.class)
        {

            String sql = "delete from LoginInfo where Login_name = ?";
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, username);

            return this.preparedStatement.executeUpdate() > 0;
        }
    }

}
