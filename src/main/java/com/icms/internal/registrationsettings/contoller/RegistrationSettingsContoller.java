package com.icms.internal.registrationsettings.contoller;

import com.icms.internal.registrationsettings.model.DateRangeForm;
import com.icms.internal.registrationsettings.model.RegistrationWindowDates;
import com.icms.internal.registrationsettings.service.RegistrationSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by matth on 7/1/2017.
 */
@RestController
@CrossOrigin
@RequestMapping ("/api/v1/admin/RegistrationSetting")
public class RegistrationSettingsContoller {

    private RegistrationSettingsService registrationSettingsService;

    @Autowired
    public RegistrationSettingsContoller(RegistrationSettingsService registrationSettingsService) {
        this.registrationSettingsService = registrationSettingsService;
    }

    @PostMapping("/newWindowDates")
    public ResponseEntity<?> saveRegistrationWindowDates(@RequestBody final DateRangeForm dateRangeForm) throws Exception {
        if(this.registrationSettingsService.saveRegistrationWindowDates(dateRangeForm)){
            return new ResponseEntity<Object>("", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/currentWindowRange")
    public ResponseEntity<RegistrationWindowDates> getCurrentRegistrationWindowRange() throws SQLException, ParseException {
        return new ResponseEntity<>(this.registrationSettingsService.getCurrentRegistrationWindowRange(), HttpStatus.OK );
    }

    @DeleteMapping("/databaseCleanUp")
    public ResponseEntity<?> dataBaseCleanUp() throws SQLException {
        if( this.registrationSettingsService.dataBaseCleanUp() ){
            return new ResponseEntity<Object>("",HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
