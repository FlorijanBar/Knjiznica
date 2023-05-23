package com.example.knjiznica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.service.KnjigaService;

@Controller
@RequestMapping("/api/knjiga")
public class KnjigaController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("knjiga", new Knjiga());
        return "create-knjiga";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Knjiga> createKnjiga(@ModelAttribute Knjiga knjiga) {
        Knjiga createdKnjiga = knjigaService.createKnjiga(knjiga);
        return new ResponseEntity<>(createdKnjiga, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public String getAllKnjiga(Model model) {
        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();
        model.addAttribute("knjige", knjige);
        return "knjige";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Knjiga> getKnjiga(@PathVariable Long id) {
        Knjiga knjiga = knjigaService.getKnjiga(id);
        if (knjiga != null) {
            return new ResponseEntity<>(knjiga, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/update")
    public String updateKnjigaForm(@PathVariable long id, Model model) {
        Knjiga knjiga = knjigaService.getKnjiga(id);
        if (knjiga != null) {
            model.addAttribute("knjiga", knjiga);
            return "update-knjiga";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/{id}/update")
    public ResponseEntity<Knjiga> updateKnjiga(@PathVariable Long id, @ModelAttribute Knjiga knjigaData) {
        Knjiga updatedKnjiga = knjigaService.updateKnjiga(id, knjigaData);
        if (updatedKnjiga != null) {
            return new ResponseEntity<>(updatedKnjiga, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   

    @GetMapping("/{id}/delete")
    public String deleteKnjigaForm(@PathVariable long id, Model model) {
        Knjiga knjiga = knjigaService.getKnjiga(id);
        if (knjiga != null) {
            model.addAttribute("knjiga", knjiga);
            return "delete-knjiga";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteKnjiga(@PathVariable Long id,Model model) {
        knjigaService.deleteKnjiga(id);
        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();
        model.addAttribute("knjige", knjige);
        return "knjige";
    }



    
    
    }


