package com.example.knjiznica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import com.example.knjiznica.model.Korisnik;
import com.example.knjiznica.repository.KorisnikRepository;
import com.example.knjiznica.service.KorisnikService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/korisnik")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;


        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("lozinka") String lozinka) {
            Korisnik korisnik = korisnikService.login(email, lozinka);
            if (korisnik != null) {
             
                return ResponseEntity.ok().build();
            } else {
             
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        
        
        @GetMapping("/login")
        public String showLoginForm(Model model) {
            model.addAttribute("korisnik", new Korisnik());
            return "login";
        }

        @PostMapping("/logout")
        public ResponseEntity<?> logout() {
           
            return ResponseEntity.ok().build();
        }
       

        @GetMapping("/{id}")
        public ResponseEntity<?> getKorisnikById(@PathVariable Long id) {
            Korisnik korisnik = korisnikService.getKorisnik(id);
            if (korisnik != null) {
                return ResponseEntity.ok(korisnik);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/all")
        public ResponseEntity<?> getAllKorisnici() {
            List<Korisnik> korisnici = korisnikService.getAllKorisnici();
            return ResponseEntity.ok(korisnici);
        }

        @PostMapping("/{id}/update")
        public ResponseEntity<?> updateKorisnik(@PathVariable Long id, @RequestBody Korisnik korisnik) {
            Korisnik updatedKorisnik = korisnikService.updateKorisnik(id, korisnik);
            if (updatedKorisnik != null) {
                return ResponseEntity.ok(updatedKorisnik);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/{id}/delete")
        public ResponseEntity<?> deleteKorisnik(@PathVariable Long id) {
            boolean deleted = korisnikService.deleteKorisnik(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        
        }
        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            model.addAttribute("korisnik", new Korisnik());
            return "register";
        }

        @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        public ResponseEntity<Korisnik> registerKorisnik(@ModelAttribute Korisnik korisnik) {
            try {
                if (korisnikService.findByEmail(korisnik.getEmail()) != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }

                Korisnik noviKorisnik = korisnikService.registerKorisnik(korisnik);

                if (noviKorisnik != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(noviKorisnik);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }



}

     





