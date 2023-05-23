package com.example.knjiznica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.repository.StudentRepository;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
    private StudentRepository studentRepository;
	
	@Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
        	existingStudent.setIme(student.getIme());
        	existingStudent.setPrezime(student.getPrezime());
            existingStudent.setStudij(student.getStudij());
            existingStudent.setGodinaStudija(student.getGodinaStudija());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
