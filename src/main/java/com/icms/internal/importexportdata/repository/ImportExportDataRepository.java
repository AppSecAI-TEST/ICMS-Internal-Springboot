package com.icms.internal.importexportdata.repository;

import com.icms.internal.DbConfig.DbConfig;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class ImportExportDataRepository
{
    private final Connection connection;
    private final ApplicationContext applicationContext;
    TreeMap<Integer, Integer> listToWrite = new TreeMap<>();
    private PreparedStatement preparedStatement = null;

    @Autowired
    public ImportExportDataRepository (final ApplicationContext applicationContext) throws SQLException
    {
        this.connection = DbConfig.getInstance();
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("Duplicates")
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
        header.add("Candidate_CollegeName");
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
            String sql = "select Candidate_ID , Candidate_FirstName, Candidate_MiddleName, Candidate_LastName , Candidate_Gender, Candidate_Email, Candidate_Phone, ci.College_Name , cm.Candidate_CollegeName from CandidateMaster as cm left join CollegeInfo as ci on  cm.Candidate_College = ci.College_ID";

            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                cellid = 0;
                row = spreadsheet.createRow(rowid++);

                Cell c1 = row.createCell(cellid++);
                c1.setCellValue( Integer.parseInt(resultSet.getString("Candidate_ID")));

                Cell c2 = row.createCell(cellid++);// Left Blank for vendor to fill apti marks.

                Cell c3 = row.createCell(cellid++);
                c3.setCellValue(resultSet.getString("Candidate_FirstName"));

                Cell c4 = row.createCell(cellid++);
                c4.setCellValue(resultSet.getString("Candidate_MiddleName"));

                Cell c5 = row.createCell(cellid++);
                c5.setCellValue(resultSet.getString("Candidate_LastName"));

                Cell c6 = row.createCell(cellid++);
                String collegeName = resultSet.getString("College_Name");
                if(null == collegeName ){
                    collegeName = resultSet.getString("Candidate_CollegeName");
                }
                c6.setCellValue(collegeName);

                Cell c7 = row.createCell(cellid++);
                c7.setCellValue(resultSet.getString("Candidate_Gender"));

                Cell c8 = row.createCell(cellid++);
                c8.setCellValue(resultSet.getString("Candidate_Email"));

                Cell c9 = row.createCell(cellid++);
                c9.setCellValue( Long.parseLong( resultSet.getString( "Candidate_Phone")) );
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


    @SuppressWarnings("Duplicates")
    public String downloadInterviewedCandidateExcel() {

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
        header.add("ID");
        header.add("FirstName");
        header.add("MiddleName");
        header.add("LastName");
        header.add("Gender");
        header.add("Email");
        header.add("Phone");
        header.add("College Name");
        header.add("Specialization");
        header.add("Graduation Start Year");
        header.add("Graduation End Year");
        header.add("Sem1");
        header.add("Sem2");
        header.add("Sem3");
        header.add("Sem4");
        header.add("Sem5");
        header.add("Sem6");
        header.add("Sem7");
        header.add("Sem8");
        header.add("Aptitude Marks");
        header.add("Tech Interview clearance");
        header.add("Tech Interviewer Name");
        header.add("Tech Interviewer Remark");
        header.add("Hr Clearance");
        header.add("Hr Interviewer Name");
        header.add("Hr Remark");

        int rowid = 0;
        int cellid = 0;
        row = spreadsheet.createRow(rowid);

        for (String key : header) {
            Cell cell = row.createCell(cellid++);
            cell.setCellValue(key);
        }

        rowid = 1;
        try {
            String sql = "Select * from InterviewMaster as im left join CandidateMaster as cm on im.Candidate_Id = cm.Candidate_ID left join CollegeInfo as ci on cm.Candidate_College = ci.College_ID left join SpecializationMaster as sm on cm.Candidate_Specialization = sm.Specialization_Id";

            this.preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                cellid = 0;
                row = spreadsheet.createRow(rowid++);

                Cell c1 = row.createCell(cellid++);
                c1.setCellValue( Integer.parseInt(resultSet.getString("Candidate_ID")));

                Cell c2 = row.createCell(cellid++);
                c2.setCellValue(resultSet.getString("Candidate_FirstName"));

                Cell c3 = row.createCell(cellid++);
                c3.setCellValue(resultSet.getString("Candidate_MiddleName"));

                Cell c4 = row.createCell(cellid++);
                c4.setCellValue(resultSet.getString("Candidate_LastName"));

                Cell c5 = row.createCell(cellid++);
                c5.setCellValue(resultSet.getString("Candidate_Gender"));

                Cell c6 = row.createCell(cellid++);
                c6.setCellValue(resultSet.getString("Candidate_Email"));

                Cell c7 = row.createCell(cellid++);
                c7.setCellValue( Long.parseLong( resultSet.getString( "Candidate_Phone")) );


                Cell c8 = row.createCell(cellid++);
                String collegeName = resultSet.getString("Candidate_CollegeName");

                if(null == collegeName){
                    collegeName = resultSet.getString("College_Name");
                }

                c8.setCellValue( collegeName );

                Cell c9 = row.createCell(cellid++);
                c9.setCellValue(resultSet.getString("Specialization_Value"));

                Cell c10 = row.createCell(cellid++);
                c10.setCellValue( Integer.parseInt( resultSet.getString("Candidate_GradStartYear") ) );

                Cell c11 = row.createCell(cellid++);
                c11.setCellValue( Integer.parseInt( resultSet.getString("Candidate_GradEndYear") ) );

                Cell c12 = row.createCell(cellid++);
                c12.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem1") ) );

                Cell c13 = row.createCell(cellid++);
                c13.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem2") ) );

                Cell c14 = row.createCell(cellid++);
                c14.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem3") ) );

                Cell c15 = row.createCell(cellid++);
                c15.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem4") ) );

                Cell c16 = row.createCell(cellid++);
                c16.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem5") ) );

                Cell c17 = row.createCell(cellid++);
                c17.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem6") ) );

                Cell c18 = row.createCell(cellid++);
                c18.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem7") ) );

                Cell c19 = row.createCell(cellid++);
                c19.setCellValue( Integer.parseInt( resultSet.getString("Candidate_Sem8") ) );

                Cell c20 = row.createCell(cellid++);
                c20.setCellValue(Integer.parseInt( resultSet.getString("Candidate_AptitudeMarks")));

                Cell c21 = row.createCell(cellid++);
                c21.setCellValue( resultSet.getString("Candidate_TechnicalClearance"));

                Cell c22 = row.createCell(cellid++);
                c22.setCellValue( resultSet.getString("Candidate_TechnicalInterviewer") );

                Cell c23 = row.createCell(cellid++);
                c23.setCellValue( resultSet.getString("Candidate_TechnicalInterviewerRemark") );

                Cell c24 = row.createCell(cellid++);
                c24.setCellValue( resultSet.getString("Candidate_HrClearance") );

                Cell c25 = row.createCell(cellid++);
                c25.setCellValue( resultSet.getString("Candidate_HrInterviewer") );

                Cell c26 = row.createCell(cellid++);
                c26.setCellValue( resultSet.getString("Candidate_HrInterviewerRemarks") );

            }


            try{
                String fileName = "Interviewed_Candidates_" + todaysDate +".xlsx";
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


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean insertDataInInterviewDB(String filePath) throws Exception
    {
        if(this.isExcelValid(filePath) && (!this.listToWrite.isEmpty())) {

            //Clear old records from table
            this.clearOldDataInInterviewTable();

            this.connection.setAutoCommit(false);
            String sql = "insert into InterviewMaster (Candidate_Id, Candidate_AptitudeMarks) values (?,?)";
            this.preparedStatement = this.connection.prepareStatement(sql);

            for (Map.Entry<Integer, Integer> entry : this.listToWrite.entrySet()){
                this.preparedStatement.setInt(1,entry.getKey());
                this.preparedStatement.setInt(2,entry.getValue());
                this.preparedStatement.addBatch();
            }
            this.preparedStatement.executeBatch();
            this.connection.commit();
            return true;

        }
        else{
            return false;
        }
    }


    private void clearOldDataInInterviewTable() throws SQLException
    {
        String sql = "delete from InterviewMaster";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.execute();
    }


    private Boolean isExcelValid(String filePath) throws Exception
    {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();


        //Check if the sheet contains 2 cells as expected.
        Row firstRow = iterator.next();
        String Candidate_ID = firstRow.getCell(0).getStringCellValue();
        String Aptitude_Marks = firstRow.getCell(1).getStringCellValue();


        if (Candidate_ID.equalsIgnoreCase("Candidate_ID") && Aptitude_Marks.equalsIgnoreCase("Aptitude_Marks"))
        {
            //clear previous values in list
            this.listToWrite.clear();

            //Check if each cell has data in proper format
            while (iterator.hasNext())
            {
                Row currentRow = iterator.next();
                int cellNos = 1;

                try{
                    ++cellNos;
                    int candidateId = (int) currentRow.getCell(0).getNumericCellValue();
                    int aptiMarks = (int) currentRow.getCell(1).getNumericCellValue();

                    this.listToWrite.put(candidateId,aptiMarks);

                    System.out.println(candidateId + "\t" + aptiMarks);
                }
                catch (Exception ex){
                    throw new Exception("Unable to parse file error occurred on row : " + cellNos );
                }
            }
            return true;
        }
        else
        {
            //columns are not properly mapped throwing error.
            throw new Exception("Invalid Columns in sheet. The first column in sheet should be Candidate_ID , Aptitude_Marks ");
        }

    }


}
