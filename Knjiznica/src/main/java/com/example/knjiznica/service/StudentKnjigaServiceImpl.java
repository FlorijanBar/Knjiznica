package com.example.knjiznica.service;
import java.util.ArrayList;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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
	    public StudentKnjiga izdajKnjigu(Student student, Knjiga knjiga, LocalDate datumIzdavanja,LocalDate rokVracanja) {
		 StudentKnjiga izdanaKnjiga = new StudentKnjiga();
	        izdanaKnjiga.setStudent(student);
	        izdanaKnjiga.setKnjiga(knjiga);
	        izdanaKnjiga.setDatumIzdavanja(LocalDate.now());
	        izdanaKnjiga.setRokVracanja(rokVracanja);
	        return studentKnjigaRepository.save(izdanaKnjiga);
	    }

	    @Override
	    public void vratiKnjigu(StudentKnjiga izdanaKnjiga) {
	        izdanaKnjiga.setDatumVracanja(LocalDate.now());
	        studentKnjigaRepository.save(izdanaKnjiga);
	    }
        @Override
	    public boolean isKnjigaIzdata(Student student, Knjiga knjiga) {
	        List<StudentKnjiga> izdaneKnjige = studentKnjigaRepository.findByStudentAndKnjiga(student, knjiga);
	        for (StudentKnjiga studentKnjiga : izdaneKnjige) {
	            if (studentKnjiga.getDatumVracanja() == null) {
	                return true; // Knjiga je izdana ako postoji izdana knjiga bez datuma vraćanja
	            }
	        }
	        return false; // Knjiga nije izdana ako ne postoji izdana knjiga bez datuma vraćanja
	    }



	    @Override
	    public List<StudentKnjiga> getIzdateKnjigeZaStudenta(Student student) {
	        return studentKnjigaRepository.findByStudent(student);
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
	    	for (StudentKnjiga izdanaKnjiga : student.getIzdateKnjige()) {
	            if (izdanaKnjiga.getKnjiga().equals(knjiga) && izdanaKnjiga.getDatumVracanja() == null) {
	                return izdanaKnjiga;
	            }
	        }
	        return null;
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
	    @Override
	    public List<StudentKnjiga> getNevraceneKnjigePoStudentu(Long studentId) {
	        LocalDate currentDate = LocalDate.now();
	        return studentKnjigaRepository.findByStudentIdAndDatumVracanjaIsNotNullAndDatumIzdavanjaBefore(studentId, currentDate);
	    }

	    @Override
	    public StudentKnjiga getIzdanaKnjigaa(Long knjigaId) {
	        return studentKnjigaRepository.findFirstByKnjigaIdAndDatumVracanjaIsNull(knjigaId);
	    }


}
