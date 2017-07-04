package com.icms.internal.importexportdata.service;

import com.icms.internal.importexportdata.repository.ImportExportDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public String downloadRegisteredCandidateExcel () throws SQLException
    {
        return this.importExportDataRepository.downloadRegisteredCandidateExcel();
    }


    public Boolean interviewFileUpload (MultipartFile file) throws Exception
    {

        if (file.isEmpty())
        {
            return false;
        }
        else
        {
            //delete file in temp dir
            File tempFile = new File(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
            if (tempFile.exists())
            {
                tempFile.delete();
            }

            // Get the file and save it in temp dir
            byte[] bytes = file.getBytes();
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
            Files.write(path, bytes);


            this.importExportDataRepository.insertDataInInterviewDB(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());

            return true;
        }
    }

    public String downloadInterviewedCandidateExcel() {
        return this.importExportDataRepository.downloadInterviewedCandidateExcel();
    }
}
