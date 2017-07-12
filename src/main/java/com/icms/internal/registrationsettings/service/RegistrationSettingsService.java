package com.icms.internal.registrationsettings.service;

import com.icms.internal.registrationsettings.model.DateRangeForm;
import com.icms.internal.registrationsettings.model.RegistrationWindowDates;
import com.icms.internal.registrationsettings.repository.RegistrationSettingsRepository;
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

    @Autowired
    public RegistrationSettingsService(RegistrationSettingsRepository registrationSettingsRepository) {
        this.registrationSettingsRepository = registrationSettingsRepository;
    }

    public boolean saveRegistrationWindowDates(final DateRangeForm dateRangeForm) throws Exception {

        if (null == dateRangeForm.getStartDate() || null == dateRangeForm.getEndDate()) {
            throw new Exception("Format exception for start or end date");
        } else {
            return this.registrationSettingsRepository.saveRegistrationWindowDates(dateRangeForm.getStartDate(), dateRangeForm.getEndDate());
        }
    }

    public RegistrationWindowDates getCurrentRegistrationWindowRange() throws SQLException, ParseException {
        return this.registrationSettingsRepository.getCurrentRegistrationWindowRange();
    }


    public boolean dataBaseCleanUp() throws SQLException
    {
        return this.registrationSettingsRepository.dataBaseCleanUp();
    }

}
