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
import org.springframework.security.crypto.password.*;

import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService {
	
	@Autowired
    private  KorisnikRepository korisnikRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public Korisnik register(Korisnik korisnik) {
	    // Perform any necessary validations, such as checking if the email is unique
		 Korisnik existingKorisnik = korisnikRepository.findByEmail(korisnik.getEmail());
		    if (existingKorisnik != null) {
		        throw new RuntimeException("Korisnik već postoji");
		    }
	    // Hash the password before saving
	 
	    // Save the user
	    return korisnikRepository.save(korisnik);
	}
	@Override
	 public Korisnik login(String email, String lozinka) {
	        // Pronađite korisnika na temelju e-mail adrese
	        Korisnik korisnik = korisnikRepository.findByEmail(email);

	        // Provjerite je li korisnik pronađen
	        if (korisnik == null) {
	            throw new IllegalArgumentException("Korisnik s navedenom e-mail adresom ne postoji.");
	        }

	     // Provjerite ispravnost unesene lozinke korisnika
	        if (!korisnik.getLozinka().equals(lozinka)) {
	            throw new IllegalArgumentException("Neispravna lozinka.");
	        }


	        return korisnik;
	    }

    @Override
    public Korisnik findByEmail(String email)
    { return korisnikRepository.findByEmail(email);
    	
    
    }
    
    
   


}

