package com.example.knjiznica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.model.Korisnik;

@Service
public interface KorisnikService {

	Korisnik register(Korisnik korisnik);

	Korisnik login(String email, String lozinka);

	Korisnik findByEmail(String email);


	UserDetails loadUserByUsername(String email);
	




}
