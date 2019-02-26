package com.sango.microservices.students.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sango.microservices.students.domain.Student;
import com.sango.microservices.students.repository.StudentRepository;

@RestController
@RequestMapping(path="/api/")
public class StudentController {
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping(path="/students", produces="application/json")
	public List<Student> getAllStudents(){
		List<Student> studentList = studentRepository.findAll();
		log.debug("From getAllStudents");
		return studentList;
	}
	
	@GetMapping(path="/load/students")
	public String loadStudents() {
		String loadStatus = "NA";
		if (studentRepository.findAll().isEmpty()) {
			Date dob1 = new Date();
			Date dob2 = new Date();
			try {
				dob1 = new SimpleDateFormat("YYYY-MM-DD").parse("2001-01-01");
				dob2 = new SimpleDateFormat("YYYY-MM-DD").parse("2002-02-02");
			} catch (ParseException pe) {
				log.error("ERROR From loadStudents while converting date error message: {} ",pe.getMessage());
			}
			List<Student> studentList = new ArrayList<Student>();
			Student student1 = new Student(UUID.randomUUID(),"John Doe",dob1,"1 Main Street","Apt 1","Claymont","DE","19301","Math");
			Student student2 = new Student(UUID.randomUUID(),"Jane Doe",dob2,"2 Main Street","Apt 2","Claymont","DE","19301","Math");
			studentList.add(student1);
			studentList.add(student2);
			try {
				studentRepository.saveAll(studentList);
				loadStatus = "Success";
				log.debug("From loadStudents: {} ",loadStatus);
			} catch (Exception e) {
				loadStatus = "Failure";
				e.getStackTrace();
				log.error("ERROR From loadStudents: {}, error message: {} ",loadStatus,e.getMessage());
			}
		} else {
			loadStatus = "Data already present";
		}
		return loadStatus;
	}

}
