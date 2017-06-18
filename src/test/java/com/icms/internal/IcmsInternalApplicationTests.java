package com.icms.internal;

import com.icms.internal.college.repository.CollegeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IcmsInternalApplicationTests {

	@Autowired
	CollegeRepository collegeRepository;

	@Test
	public void contextLoads() throws SQLException {

		collegeRepository.add();

	}

}
