package com.icms.internal.sharedapi.controller;


import com.icms.internal.sharedapi.service.SharedApiService;
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
@RequestMapping ("/Shared/api/v1")
public class SharedApiController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SharedApiController.class);
    private final SharedApiService sharedApiService;

    @Autowired
    public SharedApiController (final SharedApiService sharedApiService)
    {
        this.sharedApiService = sharedApiService;
    }

    @GetMapping ("/Countries")
    public ResponseEntity<List<String>> getCountriesList () throws SQLException, InterruptedException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.sharedApiService.getCountryList(), HttpStatus.OK);
    }

    @GetMapping ("/Cities/{countryName}")
    public ResponseEntity<List<String>> getCityList (@PathVariable ("countryName") final String countryName) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(this.sharedApiService.getCityList(countryName), HttpStatus.OK);
    }
}
