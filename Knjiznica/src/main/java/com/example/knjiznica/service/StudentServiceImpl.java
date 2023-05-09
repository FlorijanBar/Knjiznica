package com.example.knjiznica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Override
    public Student createStudent(Student Student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStudent'");
    }

    @Override
    public Iterable<Student> getAllStudent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStudent'");
    }

    @Override
    public Iterable<Student> getStudentStudij(String studij) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentStudij'");
    }

    @Override
    public Student getStudent(long id_Student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudent'");
    }

}
