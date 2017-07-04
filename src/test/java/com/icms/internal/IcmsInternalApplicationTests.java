package com.icms.internal;

import com.icms.internal.importexportdata.repository.ImportExportDataRepository;
import com.icms.internal.interviewer.model.InterviewCandidiateInfo;
import com.icms.internal.interviewer.repository.InterviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IcmsInternalApplicationTests {

	@Autowired
	ImportExportDataRepository importExportDataRepository;

	@Test
	public void contextLoads() throws Exception
	{
		System.out.println( importExportDataRepository.downloadInterviewedCandidateExcel() );

	}

}
