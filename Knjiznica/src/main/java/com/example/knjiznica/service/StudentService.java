package com.example.knjiznica.service;



import com.example.knjiznica.model.Student;

public interface StudentService {
    
	Student createStudent(Student student);

    Iterable<Student> getAllStudent();


    Student getStudent(Long id);
    
    Student updateStudent(Long id, Student student);
    
    void deleteStudent(Long id);

}
