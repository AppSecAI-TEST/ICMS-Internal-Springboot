package com.icms.internal.usermanagement.controller;

import com.icms.internal.usermanagement.model.AddUserForm;
import com.icms.internal.usermanagement.model.UserInfoAndRole;
import com.icms.internal.usermanagement.service.UserManagementService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/admin/UserManagement")
public class UserManagementController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private UserManagementService userManagementService;

    @Autowired
    public UserManagementController (final UserManagementService userManagementService)
    {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<List<UserInfoAndRole>> getAllUsers() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>( this.userManagementService.getAllUsers(), HttpStatus.OK );
    }

    @PostMapping("/AddNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody AddUserForm addUserForm) throws SQLException{
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        if(this.userManagementService.addNewUser(addUserForm)){
            return new ResponseEntity<Object>("",HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteUser/{userEmail}")
    public ResponseEntity <?> deleteUser(@PathVariable("userEmail") String userEmail ) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<Object>(this.userManagementService.deleteUser(userEmail), HttpStatus.OK);
    }

}
