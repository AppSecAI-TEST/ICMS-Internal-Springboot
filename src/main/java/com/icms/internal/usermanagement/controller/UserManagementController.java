package com.icms.internal.usermanagement.controller;

import com.icms.internal.usermanagement.model.AddUserForm;
import com.icms.internal.usermanagement.model.UserInfoAndRole;
import com.icms.internal.usermanagement.service.UserManagementService;
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
@RequestMapping("/api/v1/admin/UserManagement")
public class UserManagementController
{
    private UserManagementService userManagementService;

    @Autowired
    public UserManagementController (final UserManagementService userManagementService)
    {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<List<UserInfoAndRole>> getAllUsers() throws SQLException
    {
        return new ResponseEntity<>( this.userManagementService.getAllUsers(), HttpStatus.OK );
    }

    @PostMapping("/AddNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody AddUserForm addUserForm) throws SQLException{
        if(this.userManagementService.addNewUser(addUserForm)){
            return new ResponseEntity<Object>("",HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteUser/{userEmail}")
    public ResponseEntity <?> deleteUser(@PathVariable("userEmail") String userEmail ) throws SQLException
    {
        return new ResponseEntity<Object>(this.userManagementService.deleteUser(userEmail), HttpStatus.OK);
    }

}
