package com.example.knjiznica.service;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjizicar;
@Service
public interface KnjizicarService {
    
    Knjizicar createKnjizicar(Knjizicar Knjizicar);

    Iterable<Knjizicar> getAllKnjizicar();


    Knjizicar getKnjizicar(Long id);
    
    Knjizicar updateKnjizicar(Long id, Knjizicar knjizicar);
    
    void deleteKnjizicar(Long id);

	Knjizicar getKnjizicarByEmail(String email);

	
}
