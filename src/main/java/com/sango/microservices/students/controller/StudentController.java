package com.sango.microservices.students.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sango.microservices.students.domain.Student;
import com.sango.microservices.students.repository.StudentRepository;

@RestController
@RequestMapping(path="/api/")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping(path="/students", produces="application/json")
	public List<Student> getAllStudents(){
		List<Student> studentList = new ArrayList<Student>();
		Student student1 = new Student(UUID.randomUUID(),"John Doe","2000-01-01","1 Main Street","Apt 1","Claymont","DE","19301","Math");
		Student student2 = new Student(UUID.randomUUID(),"Jane Doe","2000-01-02","2 Main Street","Apt 2","Claymont","DE","19301","Math");
		studentList = studentRepository.findAll();
		studentList.add(student1);
		studentList.add(student2);
		return studentList;
	}

}
