package com.icms.internal.importexportdata.service;

import com.icms.internal.importexportdata.repository.ImportExportDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@Service
public class ImportExportDataService
{
    private ApplicationContext applicationContext;
    private ImportExportDataRepository importExportDataRepository;

    @Autowired
    public ImportExportDataService (final ApplicationContext applicationContext, final ImportExportDataRepository importExportDataRepository)
    {
        this.applicationContext = applicationContext;
        this.importExportDataRepository = importExportDataRepository;
    }

    public String downloadRegisteredCandidateExcel() throws SQLException
    {
        return this.importExportDataRepository.downloadRegisteredCandidateExcel();
    }
}
