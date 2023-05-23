package com.example.knjiznica.service;

import com.example.knjiznica.model.Knjizicar;

public interface KnjizicarService {
    
    Knjizicar createKnjizicar(Knjizicar Knjizicar);

    Iterable<Knjizicar> getAllKnjizicar();


    Knjizicar getKnjizicar(Long id);
    
    Knjizicar updateKnjizicar(Long id, Knjizicar knjizicar);
    
    void deleteKnjizicar(Long id);
}
