package com.icms.internal.registrationsettings.service;

import com.icms.internal.registrationsettings.model.DateRangeForm;
import com.icms.internal.registrationsettings.model.RegistrationWindowDates;
import com.icms.internal.registrationsettings.repository.RegistrationSettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by matth on 7/1/2017.
 */
@Service
public class RegistrationSettingsService {

    private RegistrationSettingsRepository registrationSettingsRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationSettingsService.class);

    @Autowired
    public RegistrationSettingsService(RegistrationSettingsRepository registrationSettingsRepository) {
        this.registrationSettingsRepository = registrationSettingsRepository;
    }

    public boolean saveRegistrationWindowDates(final DateRangeForm dateRangeForm) throws Exception {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        if (null == dateRangeForm.getStartDate() || null == dateRangeForm.getEndDate()) {
            throw new Exception("Format exception for start or end date");
        } else {
            return this.registrationSettingsRepository.saveRegistrationWindowDates(dateRangeForm.getStartDate(), dateRangeForm.getEndDate());
        }
    }

    public RegistrationWindowDates getCurrentRegistrationWindowRange() throws SQLException, ParseException {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.registrationSettingsRepository.getCurrentRegistrationWindowRange();
    }


    public boolean dataBaseCleanUp() throws SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        return this.registrationSettingsRepository.dataBaseCleanUp();
    }

}
