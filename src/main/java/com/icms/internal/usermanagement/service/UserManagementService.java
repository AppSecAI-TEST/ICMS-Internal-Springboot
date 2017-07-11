package com.icms.internal.usermanagement.service;

import com.icms.internal.usermanagement.model.AddUserForm;
import com.icms.internal.usermanagement.model.UserInfoAndRole;
import com.icms.internal.usermanagement.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class UserManagementService
{
    private UserManagementRepository userManagementRepository;
    private ApplicationContext applicationContext;

    @Autowired
    public UserManagementService (final UserManagementRepository userManagementRepository, final ApplicationContext applicationContext)
    {
        this.userManagementRepository = userManagementRepository;
        this.applicationContext = applicationContext;
    }


    public List<UserInfoAndRole> getAllUsers() throws SQLException{
        return this.userManagementRepository.getAllUsers();
    }


    public boolean addNewUser(AddUserForm addUserForm) throws SQLException
    {
        addUserForm.setUserEmail(  addUserForm.getUserEmail().replace("@infocepts.com",""));
        return this.userManagementRepository.addNewUser(addUserForm);
    }

    public boolean deleteUser(final String userEmail) throws SQLException{
        return this.userManagementRepository.deleteUser(userEmail);
    }
}
