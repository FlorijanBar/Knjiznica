package com.example.knjiznica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.repository.KnjigaRepository;
import com.example.knjiznica.repository.KnjizicarRepository;

@Service
@Transactional
public class KnjizicarServiceImpl implements KnjizicarService{

	@Autowired
    private KnjizicarRepository knjizicarRepository;
	
	@Override
    public Knjizicar createKnjizicar(Knjizicar knjizicar) {
        return knjizicarRepository.save(knjizicar);
    }

    @Override
    public Iterable<Knjizicar> getAllKnjizicar() {
        return knjizicarRepository.findAll();
    }

    

    @Override
    public Knjizicar getKnjizicar(Long id) {
        return knjizicarRepository.findById(id).orElse(null);
    }

    @Override
    public Knjizicar updateKnjizicar(Long id, Knjizicar knjizicar) {
        Knjizicar existingKnjizicar = knjizicarRepository.findById(id).orElse(null);
        if (existingKnjizicar != null) {
            existingKnjizicar.setIme(knjizicar.getIme());
            existingKnjizicar.setPrezime(knjizicar.getPrezime());
            existingKnjizicar.setEmail(knjizicar.getEmail());
            return knjizicarRepository.save(existingKnjizicar);
        }
        return null;
    }

    @Override
    public void deleteKnjizicar(Long id) {
        knjizicarRepository.deleteById(id);
    }
}
    
