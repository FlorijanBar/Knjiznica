package com.example.knjiznica.service;

import com.example.knjiznica.model.Knjiga;

public interface KnjigaService {
    
    Knjiga createKnjiga(Knjiga Knjiga);

    Iterable<Knjiga> getAllKnjiga();

    Iterable<Knjiga> getKnjigaStudij(String studij);

    Knjiga getKnjiga(long id_Knjiga);
}
