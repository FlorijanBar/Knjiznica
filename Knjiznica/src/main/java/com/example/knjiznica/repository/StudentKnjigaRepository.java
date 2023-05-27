package com.example.knjiznica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;

@Repository
public interface StudentKnjigaRepository extends JpaRepository<StudentKnjiga, Long> {

    List<StudentKnjiga> findByStudent(Student student);
    List<StudentKnjiga> findByKnjiga(Knjiga knjiga);

}
