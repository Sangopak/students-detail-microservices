package com.sango.microservices.students.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.sango.microservices.students.domain.Student;

@Repository
public interface StudentRepository extends CassandraRepository<Student, UUID>{
	
	@Query("select * from students where id=?0")
	Optional<Student> findById(UUID id);

}
