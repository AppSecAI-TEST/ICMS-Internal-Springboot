package com.icms.internal.importexportdata.controller;

import com.icms.internal.importexportdata.service.ImportExportDataService;
import net.rossillo.spring.web.mvc.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Created by Infocepts India in 2017.
 */
@RestController
@CrossOrigin
@CacheControl (maxAge = 0)
@RequestMapping("/api/v1/ImportExportData")
public class ImportExportDataController
{

    private ImportExportDataService importExportDataService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExportDataController.class);

    @Autowired
    public ImportExportDataController (final ImportExportDataService importExportDataService)
    {
        this.importExportDataService = importExportDataService;
    }

    @GetMapping("/download/RegisteredCandidateExcel")
    @SuppressWarnings("Duplicates")
    public void downloadRegisteredCandidateExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String filePath = this.importExportDataService.downloadRegisteredCandidateExcel();

        if( null == filePath)
        {
            throw new IOException("Unable to Generate Excel");
        }else {

            Path file = Paths.get(filePath);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());

            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
    }



    @GetMapping("/download/InterviewedCandidateExcel")
    @SuppressWarnings("Duplicates")
    public void downloadInterviewedCandidateExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException
    {
        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String filePath = this.importExportDataService.downloadInterviewedCandidateExcel();

        if( null == filePath)
        {
            throw new IOException("Unable to Generate Excel for interviewed candidate");
        }else {
            Path file = Paths.get(filePath);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());

            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
    }



    @PostMapping("/upload")
    public ResponseEntity<String> interviewFileUpload(@RequestParam("excelToUpload") MultipartFile file) throws Exception {

        LOGGER.debug(">> "+ new Object(){}.getClass().getEnclosingMethod().getName());

        if(this.importExportDataService.interviewFileUpload(file)){
            return new ResponseEntity<>("Data uploaded Successfully", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Unable to process request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
