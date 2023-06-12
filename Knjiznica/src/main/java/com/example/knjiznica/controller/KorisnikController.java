package com.example.knjiznica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KorisnikRepository;
import com.example.knjiznica.service.KorisnikService;
import com.example.knjiznica.service.KorisnikServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/korisnik")
public class KorisnikController {

	 private final KorisnikService korisnikService;

	    @Autowired
	    public KorisnikController(KorisnikService korisnikService) {
	        this.korisnikService = korisnikService;
	    }
    @GetMapping("/registracija")
	public String prikaziFormuRegistracije(Model model) {
		model.addAttribute("korisnik", new Korisnik());
		return "register";
	}
	
	@PostMapping("/registracija")
	public String registracijaKorisnika(Korisnik korisnik) {
		korisnikService.registracijaKorisnika(korisnik);
		return "redirect:/korisnik/prijava";
	}
	
	@GetMapping("/prijava")
	public String prikaziFormuPrijave() {
		return "login";
	}
	
	@PostMapping("/prijava")
	public String prijavaKorisnika(@RequestParam("email") String email, @RequestParam("lozinka") String lozinka) {
	    try {
	        korisnikService.prijavaKorisnika(email, lozinka);
	        return "redirect:/home";
	    } catch (BadCredentialsException e) {
	        return "redirect:/korisnik/prijava?error";
	    }
	}
	
	@GetMapping("/odjava")
	public String odjavaKorisnika() {
	    korisnikService.odjavaKorisnika();
	    return "redirect:/login";
	}





    
     

     
}
    


     





