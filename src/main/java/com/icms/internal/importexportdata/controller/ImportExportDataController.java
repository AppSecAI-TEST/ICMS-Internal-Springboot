package com.icms.internal.importexportdata.controller;

import com.icms.internal.importexportdata.service.ImportExportDataService;
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
@RequestMapping("/api/v1/ImportExportData")
public class ImportExportDataController
{

    private ImportExportDataService importExportDataService;

    @Autowired
    public ImportExportDataController (final ImportExportDataService importExportDataService)
    {
        this.importExportDataService = importExportDataService;
    }

    @GetMapping("/download/RegisteredCandidateExcel")
    public void downloadRegisteredCandidateExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException
    {
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


    @PostMapping("/upload")
    public ResponseEntity<String> singleFileUpload(@RequestParam("excelToUpload") MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            return new ResponseEntity<>("Nothing to upload", HttpStatus.BAD_REQUEST);
        }

        try {

            //delete file in temp dir
            File tempFile = new File( System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
            if(tempFile.exists()){
                tempFile.delete();
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
            Files.write(path, bytes);









            return new ResponseEntity<>("File Successfully uploaded", HttpStatus.OK );

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

}
