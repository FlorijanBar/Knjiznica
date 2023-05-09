package com.example.knjiznica.service;

import com.example.knjiznica.model.Korisnik;

public interface KorisnikService {
    
    Korisnik createKorisnik(Korisnik Korisnik);

    Iterable<Korisnik> getAllKorisnik();

    Iterable<Korisnik> getKorisnikStudij(String studij);

    Korisnik getKorisnik(long id_Korisnik);
}
