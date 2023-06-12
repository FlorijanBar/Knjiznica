package com.example.knjiznica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    private final KorisnikRepository korisnikRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserDetailsServiceImpl(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
       
    }
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Korisnik korisnik = korisnikRepository.findByEmail(email);
	    if (korisnik == null) {
	        throw new UsernameNotFoundException("Korisnik nije pronađen s emailom: " + email);
	    }

	    return new UserDetailsImpl(korisnik);
	}

	
}

