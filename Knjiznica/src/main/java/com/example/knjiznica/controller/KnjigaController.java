package com.example.knjiznica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.service.KnjigaService;

@Controller
@RequestMapping("/api/knjiga")
public class KnjigaController {
	
	    @Autowired
	    private KnjigaService knjigaService;

	    @PostMapping("/create")
	    public ResponseEntity<Knjiga> createKnjiga(@RequestBody Knjiga knjiga) {
	        Knjiga createdKnjiga = knjigaService.createKnjiga(knjiga);
	        return new ResponseEntity<>(createdKnjiga, HttpStatus.CREATED);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<Iterable<Knjiga>> getAllKnjiga() {
	        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();
	        return new ResponseEntity<>(knjige, HttpStatus.OK);
	    }

	    @GetMapping("/studij/{studij}")
	    public ResponseEntity<Iterable<Knjiga>> getKnjigaByStudij(@PathVariable String studij) {
	        Iterable<Knjiga> knjige = knjigaService.getKnjigaStudij(studij);
	        return new ResponseEntity<>(knjige, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Knjiga> getKnjigaById(@PathVariable long id_Knjiga) {
	        Knjiga knjiga = knjigaService.getKnjiga(id_Knjiga);
	        return new ResponseEntity<>(knjiga, HttpStatus.OK);
	    }
	}

