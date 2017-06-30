package com.icms.internal.ImportExportData.repository;

import com.icms.internal.DbConfig.DbConfig;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class ImportExportDataRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    private PreparedStatement preparedStatement = null;

    @Autowired
    public ImportExportDataRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    public String downloadRegisteredCandidateExcel() throws SQLException
    {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String todaysDate = dateFormat.format(date);

        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet("Registered_Candidates");
        //Create row object
        XSSFRow row;

        List<String> header = new ArrayList<>();
        header.add("Candidate_ID");
        header.add("Aptitude_Marks");
        header.add("Candidate_FirstName");
        header.add("Candidate_MiddleName");
        header.add("Candidate_LastName");
        header.add("Candidate_Gender");
        header.add("Candidate_Email");
        header.add("Candidate_Phone");

        int rowid = 0;
        int cellid = 0;
        row = spreadsheet.createRow(rowid);

        for (String key : header)
        {
            Cell cell = row.createCell(cellid++);
            cell.setCellValue(key);

        }


        rowid=1;

        try
        {
            String sql = "select Candidate_ID , Candidate_FirstName, Candidate_MiddleName, Candidate_LastName , Candidate_Gender, Candidate_Email, Candidate_Phone from CandidateMaster;";

            this.preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                cellid = 0;
                row = spreadsheet.createRow(rowid++);

                Cell c1 = row.createCell(cellid++);
                c1.setCellValue( Integer.parseInt(resultSet.getString("Candidate_ID")));

                Cell c2 = row.createCell(cellid++);

                Cell c3 = row.createCell(cellid++);
                c3.setCellValue(resultSet.getString("Candidate_FirstName"));

                Cell c4 = row.createCell(cellid++);
                c4.setCellValue(resultSet.getString("Candidate_MiddleName"));

                Cell c5 = row.createCell(cellid++);
                c5.setCellValue(resultSet.getString("Candidate_LastName"));

                Cell c6 = row.createCell(cellid++);
                c6.setCellValue(resultSet.getString("Candidate_Gender"));

                Cell c7 = row.createCell(cellid++);
                c7.setCellValue(resultSet.getString("Candidate_Email"));

                Cell c8 = row.createCell(cellid++);
                c8.setCellValue( Long.parseLong( resultSet.getString( "Candidate_Phone")) );
            }


            try{
                String fileName = "Registered_CandidatesInfo_" + todaysDate +".xlsx";
                String directory = System.getProperty("java.io.tmpdir");
                String filePath = directory+fileName;
                System.out.println(filePath);
                FileOutputStream out = new FileOutputStream(new File(filePath));
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully");


                return filePath;

            } catch (IOException e)
            {
                e.printStackTrace();
            }


        }
        catch (SQLException sqlex){
            throw sqlex;
        }

        return null;
    }
}
