package com.example.knjiznica.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService {
	
	@Autowired
    private  KorisnikRepository korisnikRepository;

    @Override
    public Korisnik login(String email, String lozinka) {
        Optional<Korisnik> korisnik = korisnikRepository.findByEmail(email);
        if (korisnik.isPresent()) {
            Korisnik prijavljeniKorisnik = korisnik.get();
            if (prijavljeniKorisnik.getLozinka().equals(lozinka)) {
                return prijavljeniKorisnik;
            }
        }
        throw new IllegalArgumentException("Pogrešna kombinacija emaila i lozinke.");
    }
    @Override
    public Korisnik registerKorisnik(Korisnik korisnik) {
        if (korisnikRepository.findByEmail(korisnik.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Korisnik s emailom " + korisnik.getEmail() + " već je registriran.");
        }
        return korisnikRepository.save(korisnik);
    }
    @Override
    public Korisnik getKorisnik(Long id) {
        return korisnikRepository.findById(id).orElse(null);
    }

    @Override
    public Korisnik updateKorisnik(Long id, Korisnik korisnikData) {
        Korisnik existingKorisnik = korisnikRepository.findById(id).orElse(null);
        if (existingKorisnik != null) {
            existingKorisnik.setEmail(korisnikData.getEmail());
            existingKorisnik.setLozinka(korisnikData.getLozinka());
            return korisnikRepository.save(existingKorisnik);
        }
        return null;
    }
    @Override
    public boolean deleteKorisnik(Long id) {
        if (korisnikRepository.existsById(id)) {
            korisnikRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public List<Korisnik> getAllKorisnici() {
        return korisnikRepository.findAll();
    }

    @Override
    public void promijeniLozinku(String email, String novaLozinka) {
        Optional<Korisnik> korisnik = korisnikRepository.findByEmail(email);
        if (korisnik.isPresent()) {
            Korisnik korisnikZaPromjenu = korisnik.get();
            korisnikZaPromjenu.setLozinka(novaLozinka);
            korisnikRepository.save(korisnikZaPromjenu);
        } else {
            throw new IllegalArgumentException("Korisnik s emailom " + email + " nije pronađen.");
        }
    }
    @Override
    public void promijeniEmail(String email, String noviEmail) {
        Optional<Korisnik> korisnik = korisnikRepository.findByEmail(email);
        if (korisnik.isPresent()) {
            Korisnik korisnikZaPromjenu = korisnik.get();
            korisnikZaPromjenu.setEmail(noviEmail);
            korisnikRepository.save(korisnikZaPromjenu);
        } else {
            throw new IllegalArgumentException("Korisnik s emailom " + email + " nije pronađen.");
        }
    }

    @Override
    public Optional<Korisnik> findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }
    
   


}

