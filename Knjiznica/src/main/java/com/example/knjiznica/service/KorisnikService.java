package com.example.knjiznica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.model.Korisnik;

import jakarta.transaction.Transactional;
@Transactional
@Service
public interface KorisnikService {

	

	Korisnik findByEmail(String email);


	boolean autentifikacijaKorisnika(Korisnik korisnik);

	void registracijaKorisnika(Korisnik korisnik);


	void prijavaKorisnika(String email, String lozinka);


	void odjavaKorisnika();


	
	




}
