package com.icms.internal.sharedApi.service;

import com.icms.internal.sharedApi.repository.SharedApiRepository;
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
    private final SharedApiRepository sharedApiRepository;

    @Autowired
    public SharedApiService (final SharedApiRepository sharedApiRepository)
    {
        this.sharedApiRepository = sharedApiRepository;
    }

    public List<String> getCountryList () throws SQLException
    {
        return this.sharedApiRepository.getCountryList();
    }

    public List<String> getCityList (String countryName) throws SQLException
    {
        return this.sharedApiRepository.getCityList(countryName);
    }
}
