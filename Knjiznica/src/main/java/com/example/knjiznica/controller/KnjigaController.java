package com.example.knjiznica.controller;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView createKnjiga(@ModelAttribute Knjiga knjiga) {
        Knjiga createdKnjiga = knjigaService.createKnjiga(knjiga);
        
        // Provjera uspješnosti stvaranja knjige
        if (createdKnjiga != null) {
            // Preusmjeravanje korisnika na stranicu koja prikazuje sve knjige
            return new ModelAndView("redirect:/api/knjiga/all");
        } else {
            // Prikazivanje poruke o greški na istoj stranici
            ModelAndView modelAndView = new ModelAndView("knjiga");
            modelAndView.addObject("errorMessage", "Došlo je do pogreške prilikom stvaranja knjige.");
            return modelAndView;
        }
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
    public ModelAndView updateKnjiga(@PathVariable Long id, @ModelAttribute Knjiga knjigaData) {
        Knjiga updatedKnjiga = knjigaService.updateKnjiga(id, knjigaData);
        if (updatedKnjiga != null) {
            RedirectView redirect = new RedirectView("/api/knjiga/all");
            return new ModelAndView(redirect);
        } else {
            return new ModelAndView("error-page"); // Stranica za prikaz greške
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
    public ModelAndView searchBooksByNaziv(@RequestParam("naziv") String naziv) {
        List<Knjiga> books = knjigaService.searchBooksByNaziv(naziv);

        // Dodavanje liste knjiga kao atributa modela
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    
    @PostMapping("/izdaj")
    public ResponseEntity<String> izdajKnjigu(@RequestParam("studentId") Long studentId, @RequestParam("knjigaId") Long knjigaId) {
        Student student = studentService.getStudent(studentId);
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (student == null || knjiga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ili knjiga nisu pronađeni.");
        }

        if (studentKnjigaService.isKnjigaIzdata(student, knjiga)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Knjiga je već izdana.");
        }

        studentKnjigaService.izdajKnjigu(student, knjiga);

        return ResponseEntity.ok("Knjiga je uspješno izdana studentu.");
    }



    // Ostali kontroleri i metode

    @GetMapping("/izdaj")
    public String showIzdavanjeKnjige(Model model) {
        Iterable<Student> studenti = studentService.getAllStudent();
        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();

        model.addAttribute("studenti", studenti);
        model.addAttribute("knjige", knjige);

        return "izdavanje-knjige"; // Naziv Thymeleaf predloška za prikazivanje stranice izdavanja knjige
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

        // Dohvat postojećeg objekta StudentKnjiga iz baze podataka
        List<StudentKnjiga> izdanaKnjiga = studentKnjigaService.findByKnjiga(knjiga);

        if (izdanaKnjiga == null) {
            return "error";
        }

        boolean izdata = true; // Postavite izdata na odgovarajuću vrijednost statusa izdane knjige

        model.addAttribute("knjiga", knjiga);
        model.addAttribute("izdata", izdata);

        return "status-izdane-knjige";
    }

    @PostMapping("/vratiKnjigu")
    public ResponseEntity<String> vratiKnjigu(@RequestParam("studentId") Long studentId, @RequestParam("knjigaId") Long knjigaId) {
        Student student = studentService.getStudent(studentId);
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (student == null || knjiga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ili knjiga nisu pronađeni.");
        }

        // Provjeri je li knjiga stvarno izdana studentu
        if (!studentKnjigaService.isKnjigaIzdata(student, knjiga)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Knjiga nije izdana studentu.");
        }

        // Stvaranje objekta StudentKnjiga s dobivenim studentom i knjigom
        StudentKnjiga izdanaKnjiga = new StudentKnjiga();
        izdanaKnjiga.setStudent(student);
        izdanaKnjiga.setKnjiga(knjiga);
        izdanaKnjiga.setDatumVracanja(LocalDate.now()); // Postavite datum vraćanja na trenutni datum

        studentKnjigaService.vratiKnjigu(izdanaKnjiga);
        return ResponseEntity.ok("Knjiga je vraćena.");
    }


    @GetMapping("/vratiKnjigu")
    public String getIzdaneKnjige(@RequestParam("studentId") Long studentId, Model model) {
        Student student = studentService.getStudent(studentId);

        if (student == null) {
            return "error";
        }

        List<StudentKnjiga> izdaneKnjige = studentKnjigaService.getAllIzdaneKnjigeByStudent(student);
        List<Knjiga> knjige = new ArrayList<>();

        for (StudentKnjiga izdanaKnjiga : izdaneKnjige) {
            knjige.add(izdanaKnjiga.getKnjiga());
        }

        model.addAttribute("student", student);
        model.addAttribute("knjige", knjige);

        return "izdane-knjige";
    }
    @GetMapping("/vraceneKnjige/{studentId}")
    public String prikaziVraceneKnjige(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        if (student == null) {
            return "error";
        }
        List<Knjiga> vraceneKnjige = studentKnjigaService.getVraceneKnjigeZaStudenta(studentId);
        model.addAttribute("student", student);
        model.addAttribute("vraceneKnjige", vraceneKnjige);
        return "vracene-knjige";
    }



    
    
    }


