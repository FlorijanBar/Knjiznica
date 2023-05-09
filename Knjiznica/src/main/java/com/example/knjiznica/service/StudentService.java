package com.example.knjiznica.service;

import com.example.knjiznica.model.Student;

public interface StudentService {
    
    Student createStudent(Student Student);

    Iterable<Student> getAllStudent();

    Iterable<Student> getStudentStudij(String studij);

    Student getStudent(long id_Student);

}
