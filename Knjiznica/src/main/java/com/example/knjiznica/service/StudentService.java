package com.example.knjiznica.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Student;
@Service
public interface StudentService {
    
	Student createStudent(Student student);

    Iterable<Student> getAllStudent();


    Student getStudent(Long id);
    
    Student updateStudent(Long id, Student student);
    
    void deleteStudent(Long id);
    public List<Student> getStudentiIstekaoRok();
}
