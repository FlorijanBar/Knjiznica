package com.example.knjiznica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.knjiznica.model.StudentKnjiga;
import com.example.knjiznica.service.EmailService;
import com.example.knjiznica.service.KnjigaService;
import com.example.knjiznica.service.StudentKnjigaService;

@Controller
@RequestMapping("/obavijest")
public class ObavijestController {
	
	 @Autowired
	    private StudentKnjigaService studentKnjigaService;
	 @Autowired
	    private EmailService emailService;
	 
	 @GetMapping("/obavijesti")
	 public String slanjeObavijesti(Model model) {
	     List<StudentKnjiga> studentiKnjigeSaProslimRokom = studentKnjigaService.getStudentiKnjigeSaProslimRokomVraćanja();
	     model.addAttribute("studentiKnjigeSaProslimRokom", studentiKnjigeSaProslimRokom);
	     return "slanje-obavijesti";
	 }



	 @GetMapping("/slanje-obavijesti")
	 public String posaljiObavijesti() {
	     List<StudentKnjiga> studentiKnjigeSaProslimRokom = studentKnjigaService.getStudentiKnjigeSaProslimRokomVraćanja();
	     for (StudentKnjiga studentKnjiga : studentiKnjigeSaProslimRokom) {
	         posaljiEmailObavijest(studentKnjiga);
	     }
	     return "redirect:/obavijest/obavijesti"; // Ispravljena putanja
	 }


	 private void posaljiEmailObavijest(StudentKnjiga studentKnjiga) {
		    String email = studentKnjiga.getStudent().getEmail();
		    String subject = "Obavijest o vraćanju knjige";
		    String message = "Poštovani " + studentKnjiga.getStudent().getIme() + ",\n\nMolimo vas da što prije vratite knjigu koju ste posudili. Hvala!";
		    try {
		        emailService.posaljiEmail(email, subject, message); // Prilagođen poziv metodi posaljiEmail
		    } catch (MailException e) {
		    	e.printStackTrace();
		        // Obrada greške slanja emaila
		    }
		}


}
