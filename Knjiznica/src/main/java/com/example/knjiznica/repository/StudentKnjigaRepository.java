package com.example.knjiznica.repository;

import java.time.LocalDate;
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
    List<StudentKnjiga> findByStudentAndKnjiga(Student student, Knjiga knjiga);
    List<StudentKnjiga> findAllByStudentAndDatumVracanjaIsNull(Student student);
    List<StudentKnjiga> findAllByStudentIdAndDatumVracanjaIsNotNull(Long studentId);
    List<StudentKnjiga> findAllByDatumVracanjaIsNullAndDatumIzdavanjaBefore(LocalDate date);
    StudentKnjiga findByStudentAndKnjigaAndDatumVracanjaIsNull(Student student, Knjiga knjiga);
    List<StudentKnjiga> findByDatumVracanjaIsNull();
    List<StudentKnjiga> findByDatumVracanjaIsNotNullAndDatumVracanjaBefore(LocalDate date);
    List<StudentKnjiga> findByStudentIdAndDatumVracanjaIsNotNullAndDatumIzdavanjaBefore(Long studentId, LocalDate currentDate);
    StudentKnjiga findFirstByKnjigaIdAndDatumVracanjaIsNull(Long knjigaId);
    }

