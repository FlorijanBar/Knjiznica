package com.example.knjiznica.service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class KorisnikServiceImpl implements KorisnikService{
	
	@Autowired
    private  KorisnikRepository korisnikRepository;
	@Autowired
    private  KnjizicarRepository knjizicarRepository;
	@Autowired
    private  KorisnikService korisnikService;



	   
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void registracijaKorisnika(Korisnik korisnik) {
		String enkriptiranaLozinka = passwordEncoder.encode(korisnik.getLozinka());
		korisnik.setLozinka(enkriptiranaLozinka);
		korisnikRepository.save(korisnik);
	}
	@Override
	public boolean autentifikacijaKorisnika(Korisnik korisnik) {
		Korisnik spremljeniKorisnik = korisnikRepository.findByEmail(korisnik.getEmail());
		if (spremljeniKorisnik != null) {
			return passwordEncoder.matches(korisnik.getLozinka(), spremljeniKorisnik.getLozinka());
		}
		return false;
	}
	@Override
	public void prijavaKorisnika(String email, String lozinka) {
	    Korisnik korisnik = korisnikRepository.findByEmail(email);
	    if (korisnik != null && passwordEncoder.matches(lozinka, korisnik.getLozinka())) {
	        // Lozinka se podudara, korisnik je uspješno prijavljen
	        // Nastavite s daljnjim radnjama
	    } else {
	        // Pogrešna e-mail adresa ili lozinka, obradite ovu situaciju
	        // Prikazivanje poruke o grešci ili izvođenje odgovarajuće radnje
	        throw new BadCredentialsException("Pogrešna e-mail adresa ili lozinka");
	    }
	}
	@Override
	public void odjavaKorisnika() {
	    SecurityContextHolder.clearContext();
	}




    @Override
    public Korisnik findByEmail(String email)
    {
    	return korisnikRepository.findByEmail(email);
    
    }

   
   
    
    
   
    
}
    




