package com.example.knjiznica.service;

import com.example.knjiznica.model.Knjizicar;

public interface KnjizicarService {
    
    Knjizicar createKnjizicar(Knjizicar Knjizicar);

    Iterable<Knjizicar> getAllKnjizicar();

    Iterable<Knjizicar> getKnjizicarStudij(String studij);

    Knjizicar getKnjizicar(long id_Knjizicar);
}
