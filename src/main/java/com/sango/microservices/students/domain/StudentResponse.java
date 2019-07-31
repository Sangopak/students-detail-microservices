package com.sango.microservices.students.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    private List<Student> students;
}
