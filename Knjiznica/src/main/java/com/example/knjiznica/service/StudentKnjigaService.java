package com.example.knjiznica.service;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.StudentKnjiga;
@Service
public interface StudentKnjigaService {
    
    StudentKnjiga createStudentKnjiga(StudentKnjiga StudentKnjiga);

    Iterable<StudentKnjiga> getAllStudentKnjiga();

    Iterable<StudentKnjiga> getStudentKnjigaStudij(String studij);

    StudentKnjiga getStudentKnjiga(long id_StudentKnjiga);
}
