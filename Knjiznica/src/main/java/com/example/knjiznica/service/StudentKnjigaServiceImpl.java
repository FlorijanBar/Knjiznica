package com.example.knjiznica.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;
import com.example.knjiznica.repository.KnjigaRepository;
import com.example.knjiznica.repository.StudentKnjigaRepository;
import com.example.knjiznica.repository.StudentRepository;

@Service
public class StudentKnjigaServiceImpl implements StudentKnjigaService{
    
	
	@Autowired
    private StudentKnjigaRepository studentKnjigaRepository;
	
	 @Override
	    public StudentKnjiga izdajKnjigu(Student student, Knjiga knjiga) {
		 StudentKnjiga izdanaKnjiga = new StudentKnjiga();
	        izdanaKnjiga.setStudent(student);
	        izdanaKnjiga.setKnjiga(knjiga);
	        izdanaKnjiga.setDatumIzdavanja(LocalDate.now());
	        // Postavite datum vraćanja prema potrebama
	        // Možete dodati dodatnu logiku ovdje, poput provjere dostupnosti knjige
	        // i ažuriranja statusa knjige
	        return studentKnjigaRepository.save(izdanaKnjiga);
	    }

	    @Override
	    public void vratiKnjigu(StudentKnjiga izdanaKnjiga) {
	        izdanaKnjiga.setDatumVracanja(LocalDate.now());
	        // Dodajte dodatnu logiku ovdje, poput ažuriranja statusa knjige
	        studentKnjigaRepository.save(izdanaKnjiga);
	    }

	    @Override
	    public boolean isKnjigaIzdata(Knjiga knjiga) {
	        List<StudentKnjiga> izdateKnjige = studentKnjigaRepository.findByKnjiga(knjiga);
	        return !izdateKnjige.isEmpty();
	    }

	    @Override
	    public List<StudentKnjiga> getIzdateKnjigeZaStudenta(Student student) {
	        return studentKnjigaRepository.findByStudent(student);
	    }
    
}
