package com.example.knjiznica.service;
import java.util.ArrayList;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;
import com.example.knjiznica.repository.KnjigaRepository;
import com.example.knjiznica.repository.StudentKnjigaRepository;
import com.example.knjiznica.repository.StudentRepository;
import java.util.HashMap;
import java.util.Map;
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
	        izdanaKnjiga.setDatumVracanja(LocalDate.now().plusDays(30));
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
	    public boolean isKnjigaIzdata(Student student, Knjiga knjiga) {
	        List<StudentKnjiga> izdateKnjige = studentKnjigaRepository.findByKnjiga(knjiga);
	        return izdateKnjige.stream().anyMatch(sk -> sk.getStudent().equals(student));
	    }


	    @Override
	    public List<StudentKnjiga> getIzdateKnjigeZaStudenta(Student student) {
	        return studentKnjigaRepository.findByStudent(student);
	    }
	    @Override
	    public StudentKnjiga findByStudentAndKnjiga(Student student, Knjiga knjiga) {
	        return studentKnjigaRepository.findByStudentAndKnjiga(student, knjiga);
	    }
	    @Override
	    public List<StudentKnjiga> findByKnjiga(Knjiga knjiga) {
	        return studentKnjigaRepository.findByKnjiga(knjiga);
	    }
	    @Override
	    public List<StudentKnjiga> getAllIzdaneKnjigeByStudent(Student student) {
	        return studentKnjigaRepository.findAllByStudentAndDatumVracanjaIsNull(student);
	    }
	    @Override
	    public List<Knjiga> getVraceneKnjigeZaStudenta(Long studentId) {
	        List<StudentKnjiga> vraceneKnjige = studentKnjigaRepository.findAllByStudentIdAndDatumVracanjaIsNotNull(studentId);
	        List<Knjiga> knjige = new ArrayList<>();
	        for (StudentKnjiga studentKnjiga : vraceneKnjige) {
	            knjige.add(studentKnjiga.getKnjiga());
	        }
	        return knjige;
	    }
	   
	    @Override
	    public List<StudentKnjiga> getOverdueKnjige() {
	        LocalDate currentDate = LocalDate.now();
	        return studentKnjigaRepository.findAllByDatumVracanjaIsNullAndDatumIzdavanjaBefore(currentDate);
	    }
	    
	

	 
	    @Override
	    public StudentKnjiga getIzdanaKnjiga(Student student, Knjiga knjiga) {
	        return studentKnjigaRepository.findByStudentAndKnjigaAndDatumVracanjaIsNull(student,knjiga);
	    }
	    @Override
	    public List<StudentKnjiga> getKnjigeNisuVracene() {
	        return studentKnjigaRepository.findByDatumVracanjaIsNull();
	    }
	    @Override
	    public List<StudentKnjiga> getNevraceneKnjige() {
	        LocalDate currentDate = LocalDate.now();
	        return studentKnjigaRepository.findByDatumVracanjaIsNotNullAndDatumVracanjaBefore(currentDate);
	    }

	    @Override
	    public Optional<StudentKnjiga> findById(Long id) {
	        return studentKnjigaRepository.findById(id);
	    }
	    


}
