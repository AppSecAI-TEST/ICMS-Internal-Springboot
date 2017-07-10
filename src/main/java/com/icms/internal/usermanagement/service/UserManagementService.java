package com.icms.internal.usermanagement.service;

import com.icms.internal.usermanagement.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
}
