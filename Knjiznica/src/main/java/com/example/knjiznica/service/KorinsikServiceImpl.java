package com.example.knjiznica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Korisnik;

@Service
@Transactional
public class KorinsikServiceImpl implements KorisnikService{

    @Override
    public Korisnik createKorisnik(Korisnik Korisnik) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createKorisnik'");
    }

    @Override
    public Iterable<Korisnik> getAllKorisnik() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllKorisnik'");
    }

    @Override
    public Iterable<Korisnik> getKorisnikStudij(String studij) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKorisnikStudij'");
    }

    @Override
    public Korisnik getKorisnik(long id_Korisnik) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKorisnik'");
    }
    
}
