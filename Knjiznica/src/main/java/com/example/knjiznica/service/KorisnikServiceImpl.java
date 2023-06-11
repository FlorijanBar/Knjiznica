package com.example.knjiznica.service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.*;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KnjizicarRepository;
import com.example.knjiznica.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService, UserDetailsService {
	
	@Autowired
    private  KorisnikRepository korisnikRepository;
	@Autowired
    private  KnjizicarRepository knjizicarRepository;
	



	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Korisnik korisnik = korisnikRepository.findByEmail(email);
	        if (korisnik == null) {
	            throw new UsernameNotFoundException("Korisnik nije pronađen: " + email);
	        }

	        return User.builder()
	                .username(korisnik.getEmail())
	                .password(korisnik.getLozinka())
	                .roles("KORISNIK")
	                .build();
	    }

	    @Override
	    public Korisnik register(Korisnik korisnik) {
	        Korisnik existingKorisnik = korisnikRepository.findByEmail(korisnik.getEmail());
	        if (existingKorisnik != null) {
	            throw new RuntimeException("Korisnik već postoji");
	        }

	        return korisnikRepository.save(korisnik);
	    }

	    @Override
	    public Korisnik login(String email, String lozinka) {
	        Korisnik korisnik = korisnikRepository.findByEmail(email);
	        if (korisnik == null) {
	            throw new UsernameNotFoundException("Korisnik s navedenom e-mail adresom ne postoji.");
	        }

	        if (!korisnik.getLozinka().equals(lozinka)) {
	            throw new BadCredentialsException("Neispravna kombinacija e-maila i lozinke.");
	        }

	        return korisnik;
	    }

    @Override
    public Korisnik findByEmail(String email)
    { return korisnikRepository.findByEmail(email);
    
    }

   
   
    
    
   
    
}
    




