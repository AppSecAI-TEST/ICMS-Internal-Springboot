package com.icms.internal.sharedApi.controller;

import com.icms.internal.sharedApi.repository.SharedApiRepository;
import com.icms.internal.sharedApi.service.SharedApiService;
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
@RequestMapping("/Shared/api/v1")
public class SharedApiController
{
    private final SharedApiService sharedApiService;

    @Autowired
    public SharedApiController (final SharedApiService sharedApiService)
    {
        this.sharedApiService = sharedApiService;
    }

    @GetMapping("/Countries")
    public ResponseEntity<List<String>> getCountriesList() throws SQLException
    {
        return new ResponseEntity<>(this.sharedApiService.getCountryList(), HttpStatus.OK);
    }

    @GetMapping("/Cities/{countryName}")
    public ResponseEntity<List<String>> getCityList(@PathVariable("countryName") final String countryName) throws SQLException
    {
        return new ResponseEntity<>(this.sharedApiService.getCityList(countryName) ,HttpStatus.OK );
    }
}
