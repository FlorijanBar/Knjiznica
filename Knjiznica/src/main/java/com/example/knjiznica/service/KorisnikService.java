package com.example.knjiznica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.knjiznica.model.Korisnik;

public interface KorisnikService {
    
    

	Optional<Korisnik> findByEmail(String email);
	Korisnik registerKorisnik(Korisnik korisnik);
	void promijeniEmail(String email, String noviEmail);
	void promijeniLozinku(String email, String novaLozinka);
	Korisnik login(String email, String lozinka);
	Korisnik getKorisnik(Long id);
	Korisnik updateKorisnik(Long id, Korisnik korisnikData);
	boolean deleteKorisnik(Long id);
	List<Korisnik> getAllKorisnici();
}
