package com.example.knjiznica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.repository.KnjigaRepository;

@Service
public class KnjigaServiceImpl implements KnjigaService{

	 @Autowired
	    private KnjigaRepository knjigaRepository;

	
	 @Override
	    public Knjiga createKnjiga(Knjiga knjiga) {
	        return knjigaRepository.save(knjiga);
	    }

	    @Override
	    public Iterable<Knjiga> getAllKnjiga() {
	        return knjigaRepository.findAll();
	    }

	    @Override
	    public Knjiga getKnjiga(Long id) {
	        return knjigaRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Knjiga updateKnjiga(Long id, Knjiga knjiga) {
	        Knjiga existingKnjiga = knjigaRepository.findById(id).orElse(null);
	        if (existingKnjiga != null) {
	            existingKnjiga.setNaziv(knjiga.getNaziv());
	            existingKnjiga.setAutor(knjiga.getAutor());
	            existingKnjiga.setGodina_izdanja(knjiga.getGodina_izdanja());
	            existingKnjiga.setBroj_stranica(knjiga.getBroj_stranica());
	            existingKnjiga.setOznaka(knjiga.getOznaka());
	            existingKnjiga.setInventurni_broj(knjiga.getInventurni_broj());
	            return knjigaRepository.save(existingKnjiga);
	        }
	        return null;
	    }

	    @Override
	    public void deleteKnjiga(Long id) {
	        knjigaRepository.deleteById(id);
	    }
	    @Override
	    public List<Knjiga> searchBooksByNaziv(String naziv) {
	        return knjigaRepository.findByNazivContainingIgnoreCase(naziv);
	    }
}
