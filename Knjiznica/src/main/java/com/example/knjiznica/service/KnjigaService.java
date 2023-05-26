package com.example.knjiznica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjiga;

@Service
public interface KnjigaService {
    
	 Knjiga createKnjiga(Knjiga knjiga);
	 
	    Iterable<Knjiga> getAllKnjiga();
	    
	    Knjiga getKnjiga(Long id);
	    
	    Knjiga updateKnjiga(Long id, Knjiga knjiga);
	    
	    void deleteKnjiga(Long id);

		List<Knjiga> searchBooksByNaziv(String naziv);

		
	    
}
