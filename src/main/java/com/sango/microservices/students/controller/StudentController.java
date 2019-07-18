package com.sango.microservices.students.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.sango.microservices.students.domain.Student;
import com.sango.microservices.students.exception.NoStudentFoundException;
import com.sango.microservices.students.exception.StudentNotFoundException;
import com.sango.microservices.students.repository.StudentRepository;
import com.sango.microservices.students.util.CommonConstant;

@Slf4j
@RestController
@RequestMapping(path="/api/")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping(path="/students", produces="application/json")
	public List<Student> getAllStudents(){
		List<Student> studentList = studentRepository.findAll();
		if(studentList.isEmpty()) {
			log.error("From getAllStudents "+CommonConstant.NO_STUDENT_FOUND);
			throw new NoStudentFoundException(CommonConstant.NO_STUDENT_FOUND);
		}
		log.info("From getAllStudents total student count {} ",studentList.size());
		return studentList;
	}
	
	@GetMapping(path="/students/{id}",produces="application/json")
	public Optional<Student> getStudentById(@PathVariable String id) {
		Optional<Student> resultStudent = studentRepository.findById(UUID.fromString(id));
		if (!resultStudent.isPresent()) {
			log.error("From getStudentById "+CommonConstant.STUDENT_NOT_FOUND+" {} "+id);
			throw new StudentNotFoundException(CommonConstant.STUDENT_NOT_FOUND+id);
		}
		log.info("From getStudentById student found with id {} ",id);
		return resultStudent;
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
			loadStatus = "Some Data already present";
		}
		return loadStatus;
	}

}
