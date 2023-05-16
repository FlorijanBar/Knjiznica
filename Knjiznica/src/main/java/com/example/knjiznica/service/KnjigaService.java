package com.example.knjiznica.service;

import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjiga;

@Service
public interface KnjigaService {
    
    Knjiga createKnjiga(Knjiga knjiga);

    Iterable<Knjiga> getAllKnjiga();

    Iterable<Knjiga> getKnjigaStudij(String studij);
   
    Knjiga getKnjiga(long id_Knjiga);
}
