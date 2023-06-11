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

    @Autowired
    private KorisnikService korisnikService;
   
    @Autowired
    private KorisnikRepository korisnikRepository;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(@ModelAttribute Korisnik korisnik) {
        // Provjerite postoji li korisnik s istom email adresom
        Korisnik existingKorisnik = korisnikService.findByEmail(korisnik.getEmail());
        if (existingKorisnik != null) {
            String errorMessage = "Korisnik već postoji";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        try {
            // Registrirajte korisnika
            Korisnik registeredKorisnik = korisnikService.register(korisnik);
            return ResponseEntity.ok(registeredKorisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

   

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String lozinka, Model model) {
        try {
            korisnikService.login(email, lozinka);
            return "redirect:/home";
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            model.addAttribute("error", "Neispravna kombinacija emaila i lozinke.");
            return "login";
        }
    }




    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "login";
    }


    
     

     
}
    


     





