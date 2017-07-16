package com.icms.internal.HealthStatus.service;

import com.icms.internal.HealthStatus.repository.HealthStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by matth on 7/15/2017.
 */
@Service
public class HealthStatusService {

    private HealthStatusRepository healthStatusRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthStatusService.class);

    @Autowired
    public HealthStatusService(HealthStatusRepository healthStatusRepository) {
        this.healthStatusRepository = healthStatusRepository;
    }

    public String getTotalCandidatesRegistered() throws SQLException{
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.healthStatusRepository.getTotalCandidatesRegistered();
    }

    public String getTotalCandidatesToInterview() throws SQLException{
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.healthStatusRepository.getTotalCandidatesToInterview();
    }

    public String getTotalCandidatesInterviewed() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.healthStatusRepository.getTotalCandidatesInterviewed();
    }

    public String getTotalCandidatesSelected() throws SQLException {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return this.healthStatusRepository.getTotalCandidatesSelected();
    }
}
