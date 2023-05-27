package com.example.knjiznica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;
import com.example.knjiznica.repository.StudentKnjigaRepository;
import com.example.knjiznica.service.KnjigaService;
import com.example.knjiznica.service.StudentKnjigaService;
import com.example.knjiznica.service.StudentService;

@Controller
@RequestMapping("/api/knjiga")
public class KnjigaController {

    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private StudentKnjigaService studentKnjigaService;

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

    @GetMapping("/search")
    public ResponseEntity<List<Knjiga>> searchBooksByNaziv(@RequestParam("naziv") String naziv) {
        List<Knjiga> books = knjigaService.searchBooksByNaziv(naziv);
        return ResponseEntity.ok(books);
    }
    
    @PostMapping("/{knjigaId}/izdaj/{studentId}")
    public ResponseEntity<String> izdajKnjigu(@PathVariable("knjigaId") Long knjigaId, @PathVariable("studentId") Long studentId) {
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);
        Student student = studentService.getStudent(studentId);

        if (knjiga == null || student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Knjiga ili student nisu pronađeni.");
        }

        if (studentKnjigaService.isKnjigaIzdata(knjiga)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Knjiga je već izdana.");
        }

        studentKnjigaService.izdajKnjigu(student, knjiga);

        return ResponseEntity.ok("Knjiga je uspješno izdana studentu.");
    }


    // Ostali kontroleri i metode

    @GetMapping("/{knjigaId}/izdaj/{studentId}")
    public String showIzdavanjeKnjigeForm(@PathVariable("knjigaId") Long knjigaId, @PathVariable("studentId") Long studentId, Model model) {
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);
        Student student = studentService.getStudent(studentId);

        if (knjiga == null || student == null) {
            return "error";
        }

        model.addAttribute("knjiga", knjiga);
        model.addAttribute("student", student);

        Iterable<Student> studenti = studentService.getAllStudent();
        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();
        model.addAttribute("studenti", studenti);
        model.addAttribute("knjige", knjige);

        return "izdavanje-knjige";
    }


    @GetMapping("/student/{studentId}/izdane-knjige")
    public String getIzdaneKnjigeZaStudenta(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.getStudent(studentId);

        if (student == null) {
            return "error";
        }

        List<StudentKnjiga> izdaneKnjige = studentKnjigaService.getIzdateKnjigeZaStudenta(student);
        model.addAttribute("student", student);
        model.addAttribute("izdaneKnjige", izdaneKnjige);

        return "pregled-izdanih-knjiga";
    }

    @GetMapping("/{knjigaId}/status")
    public String provjeriStatusIzdaneKnjige(@PathVariable("knjigaId") Long knjigaId, Model model) {
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (knjiga == null) {
            return "error";
        }

        boolean izdata = studentKnjigaService.isKnjigaIzdata(knjiga);
        model.addAttribute("knjiga", knjiga);
        model.addAttribute("izdata", izdata);

        return "status-izdane-knjige";
    }


    
    
    }


