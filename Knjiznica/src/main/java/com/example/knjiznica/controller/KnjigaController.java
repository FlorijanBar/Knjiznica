package com.example.knjiznica.controller;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.model.StudentKnjiga;
import com.example.knjiznica.repository.StudentKnjigaRepository;
import com.example.knjiznica.service.EmailService;
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
    
    @Autowired
    private EmailService emailService; // Trebate prilagoditi ovisno o implementaciji vaše usluge slanja e-pošte

    @Autowired
    private  JavaMailSender mailSender;
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

    
    




  

    @GetMapping("/{knjigaId}/status")
    public String provjeriStatusIzdaneKnjige(@PathVariable("knjigaId") Long knjigaId,
                                             @RequestParam(value = "studentId", required = false) Long studentId,
                                             Model model) {
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (knjiga == null) {
            return "error";
        }

        if (studentId != null) {
            Student student = studentService.getStudent(studentId);
            StudentKnjiga izdanaKnjiga = studentKnjigaService.getIzdanaKnjigaZaStudenta(knjigaId, studentId);

            if (izdanaKnjiga == null || izdanaKnjiga.getDatumVracanja() != null) {
                model.addAttribute("knjiga", knjiga);
                model.addAttribute("statusPoruka", "Knjiga nije izdana ovom studentu.");
                return "status-izdane-knjige";
            }

            model.addAttribute("izdanaKnjiga", izdanaKnjiga);
            model.addAttribute("student", student);

            return "status-izdane-knjige-student";
        } else {
            List<StudentKnjiga> izdaneKnjige = studentKnjigaService.getIzdateKnjigeZaKnjigu(knjiga);

            if (izdaneKnjige.isEmpty()) {
                model.addAttribute("knjiga", knjiga);
                model.addAttribute("statusPoruka", "Knjiga nije izdana nijednom studentu.");
                return "status-izdane-knjige";
            }

            model.addAttribute("izdaneKnjige", izdaneKnjige);

            return "status-izdane-knjige";
        }
    }





    


   

    @GetMapping("/nevraceneKnjige")
    public String getPrikaziNevraceneKnjige(@RequestParam("studentId") Long studentId, Model model) {
        List<StudentKnjiga> nevraceneKnjige = studentKnjigaService.getNevraceneKnjigePoStudentu(studentId);
        model.addAttribute("nevraceneKnjige", nevraceneKnjige);
        return "nevracene-knjige-zakasnjele";
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


    @GetMapping("/printBorrowedBooks")
    public String printBorrowedBooks(Model model) {
        List<StudentKnjiga> borrowedKnjige = studentKnjigaService.getAllIzdaneKnjigeByStudent(null);
        model.addAttribute("borrowedKnjige", borrowedKnjige);
        return "print-borrowed-books";
    }



   
    

    @PostMapping("/posaljiObavijest")
    public ResponseEntity<String> posaljiObavijest(@RequestParam("studentKnjigaId") Long studentKnjigaId) {
        Optional<StudentKnjiga> optionalStudentKnjiga = studentKnjigaService.findById(studentKnjigaId);
        if (optionalStudentKnjiga.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("StudentKnjiga nije pronađena.");
        }

        StudentKnjiga studentKnjiga = optionalStudentKnjiga.get();

        // Logika slanja obavijesti
        if (studentKnjiga.getDatumVracanja() != null && studentKnjiga.getDatumVracanja().isBefore(LocalDate.now())) {
            // Datum vraćanja je prošao, šaljemo obavijest
            String primalac = studentKnjiga.getStudent().getEmail();
            String naslov = "Vaša knjiga je istekla";
            String poruka = "Poštovani, \n\nOva poruka je automatska obavijest da je rok za vraćanje knjige istekao. Molimo vas da knjigu vratite u najkraćem mogućem roku.\n\nHvala.";
            
            // Slanje emaila
            emailService.posaljiEmail(primalac, naslov, poruka);
        }

        return ResponseEntity.ok("Obavijest je poslana.");
    }

    @PostMapping("/izdaj")
    public ResponseEntity<String> izdajKnjigu(
        @RequestParam("studentId") Long studentId,
        @RequestParam("knjigaId") Long knjigaId,
        @RequestParam("datumIzdavanja") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate datumIzdavanja,
        @RequestParam("rokVracanja") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate rokVracanja
    ) {
        Student student = studentService.getStudent(studentId);
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (student == null || knjiga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ili knjiga nisu pronađeni.");
        }

        if (studentKnjigaService.isKnjigaIzdata(student, knjiga)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Knjiga je već izdana.");
        }

        StudentKnjiga izdanaKnjiga = studentKnjigaService.izdajKnjigu(student, knjiga, datumIzdavanja, rokVracanja);
        izdanaKnjiga.setDatumVracanja(null);

        if (izdanaKnjiga == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom izdavanja knjige.");
        }

        return ResponseEntity.ok("Knjiga je uspješno izdana studentu.");
    }

    @PostMapping("/vratiKnjigu")
    public ResponseEntity<String> vratiKnjigu(
        @RequestParam("studentId") Long studentId,
        @RequestParam("knjigaId") Long knjigaId,
        @RequestParam("datumVracanja") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate datumVracanja
    ) {
        Student student = studentService.getStudent(studentId);
        Knjiga knjiga = knjigaService.getKnjiga(knjigaId);

        if (student == null || knjiga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ili knjiga nisu pronađeni.");
        }

        StudentKnjiga izdanaKnjiga = studentKnjigaService.getIzdanaKnjiga(student, knjiga);

        if (izdanaKnjiga == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Knjiga nije izdana studentu.");
        }

        if (izdanaKnjiga.getDatumVracanja() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Knjiga je već vraćena.");
        }
      

        if (datumVracanja == null) {
          
            datumVracanja = LocalDate.now();
        }
       
        izdanaKnjiga.setDatumVracanja(datumVracanja);
        studentKnjigaService.vratiKnjigu(izdanaKnjiga);

        return ResponseEntity.ok("Knjiga je vraćena.");
    }

    @GetMapping("/izdaj")
    public String showIzdavanjeKnjige(Model model) {
        Iterable<Student> studenti = studentService.getAllStudent();
        Iterable<Knjiga> knjige = knjigaService.getAllKnjiga();

        model.addAttribute("studenti", studenti);
        model.addAttribute("knjige", knjige);

        return "izdavanje-knjige"; 
    }

    @GetMapping("/student/{studentId}/izdane-knjige")
    public String prikaziIzdaneKnjigeZaStudenta(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        
        if (student == null) {
            return "error";
        }
        
        List<StudentKnjiga> izdaneKnjige = studentKnjigaService.getIzdaneKnjigeZaStudenta(studentId);
        
        model.addAttribute("student", student);
        model.addAttribute("izdaneKnjige", izdaneKnjige);
        
        return "izdane-knjige";
    }

    
    
    
    
    }


