package com.icms.internal.sharedapi.service;


import com.icms.internal.sharedapi.repository.SharedApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class SharedApiService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SharedApiService.class);
    private final SharedApiRepository sharedApiRepository;

    @Autowired
    public SharedApiService (final SharedApiRepository sharedApiRepository)
    {
        this.sharedApiRepository = sharedApiRepository;
    }

    public List<String> getCountryList () throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.sharedApiRepository.getCountryList();
    }

    public List<String> getCityList (String countryName) throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.sharedApiRepository.getCityList(countryName);
    }
}
