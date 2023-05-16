package com.example.knjiznica.service;

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
        // TODO Auto-generated method stub
    }

    @Override
    public Iterable<Knjiga> getAllKnjiga() {
    	return knjigaRepository.findAll();
    }

    @Override
    public Iterable<Knjiga> getKnjigaStudij(String studij) {
    	return knjigaRepository.findStudij(studij);
    }

    @Override
    public Knjiga getKnjiga(long id_Knjiga) {
    	
    	Optional<Knjiga> knjiga = knjigaRepository.findById(id_Knjiga);
        return knjiga.orElse(null);
    }
    
}
