package com.sango.microservices.students.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value="students")
public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name="id",type=PrimaryKeyType.PARTITIONED)
	private UUID id;
	@PrimaryKeyColumn(name="name",type=PrimaryKeyType.CLUSTERED, ordering=Ordering.DESCENDING)
	private String name;
	@PrimaryKeyColumn(name="dob",type=PrimaryKeyType.CLUSTERED, ordering=Ordering.DESCENDING)
	private Date dob;
	private String street;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String course;

}
