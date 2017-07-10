package com.icms.internal.usermanagement.controller;

import com.icms.internal.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
public class UserManagementController
{
    private UserManagementService userManagementService;

    @Autowired
    public UserManagementController (final UserManagementService userManagementService)
    {
        this.userManagementService = userManagementService;
    }
}
