package com.example.knjiznica.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	
	 @Autowired
	private  JavaMailSender javaMailSender;
	 
	 public void posaljiEmail(String primalac, String naslov, String poruka) {
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(primalac);
	        email.setSubject(naslov);
	        email.setText(poruka);

	        javaMailSender.send(email);
	    }
}
