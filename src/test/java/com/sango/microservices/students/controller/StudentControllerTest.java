package com.sango.microservices.students.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sango.microservices.students.domain.Student;
import com.sango.microservices.students.domain.StudentResponse;
import com.sango.microservices.students.repository.StudentRepository;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private StudentResponse expectedStudentResponse = new StudentResponse();
    private Student expectedStudent;
    private StudentResponse actualStudentResponse = new StudentResponse();
    private Student actualStudent;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        expectedStudent = objectMapper.readValue(new ClassPathResource("student.json").getFile(),Student.class);
        expectedStudentResponse = objectMapper.readValue(new ClassPathResource("student-response.json").getFile(), StudentResponse.class);
    }

    @Test
    public void getAllStudents() throws Exception{
        Mockito.when(studentRepository.findAll()).thenReturn(expectedStudentResponse.getStudents());
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/students")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(responseString).isNotNull();
        actualStudentResponse = objectMapper.readValue(responseString,StudentResponse.class);
        Assert.assertTrue(CollectionUtils.isEqualCollection(actualStudentResponse.getStudents(),expectedStudentResponse.getStudents()));
    }

    @Test
    public void getStudentById() {
    }

    @Test
    public void loadStudents() {
    }
}