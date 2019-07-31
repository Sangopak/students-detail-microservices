package com.sango.microservices.students;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.sango.microservices.students.controller.StudentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentsApplication.class)
public class StudentsApplicationTest {
	@MockBean
	private StudentsApplication studentsApplication;

	@InjectMocks
	private StudentController studentController;
	
	@Test
	public void test_Conext_Load() {
		assertNotNull(studentsApplication);
	}
}
