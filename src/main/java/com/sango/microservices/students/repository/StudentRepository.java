package com.sango.microservices.students.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sango.microservices.students.domain.Student;

@Repository
public interface StudentRepository extends CassandraRepository<Student, UUID>{

}
