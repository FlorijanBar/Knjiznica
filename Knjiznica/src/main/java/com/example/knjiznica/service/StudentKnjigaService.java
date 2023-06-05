package com.example.knjiznica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;
@Service
public interface StudentKnjigaService {

	

	void vratiKnjigu(StudentKnjiga izdanaKnjiga);

	

	List<StudentKnjiga> getIzdateKnjigeZaStudenta(Student student);

	

	List<StudentKnjiga> findByKnjiga(Knjiga knjiga);

	boolean isKnjigaIzdata(Student student, Knjiga knjiga);

	List<StudentKnjiga> getAllIzdaneKnjigeByStudent(Student student);

	List<Knjiga> getVraceneKnjigeZaStudenta(Long studentId);


	List<StudentKnjiga> getOverdueKnjige();


	StudentKnjiga getIzdanaKnjiga(Student student, Knjiga knjiga);

	List<StudentKnjiga> getKnjigeNisuVracene();

	

	Optional<StudentKnjiga> findById(Long id);


	List<StudentKnjiga> getNevraceneKnjige();


	List<StudentKnjiga> getNevraceneKnjigePoStudentu(Long studentId);

	StudentKnjiga izdajKnjigu(Student student, Knjiga knjiga, LocalDate datumIzdavanja,LocalDate rokVracanja);



	StudentKnjiga getIzdanaKnjigaa(Long knjigaId);




	

	

	
    
    
}
