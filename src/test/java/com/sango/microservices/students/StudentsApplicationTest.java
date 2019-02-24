package com.sango.microservices.students;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StudentsApplicationTest {
	@MockBean
	StudentsApplication studentsApplication;
	
	@Test
	public void test_Conext_Load() {
		assertNotNull(studentsApplication);
	}
}
