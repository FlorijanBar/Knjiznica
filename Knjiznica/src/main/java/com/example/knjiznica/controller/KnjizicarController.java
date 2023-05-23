package com.example.knjiznica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.knjiznica.model.Knjiga;
import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.service.KnjizicarService;

@Controller
@RequestMapping("/knjizicar")
public class KnjizicarController {
	
	@Autowired
    private KnjizicarService knjizicarService;
	
	
	 @GetMapping("/all")
	    public String getAllKnjizicar(Model model) {
	        Iterable<Knjizicar> knjizicari = knjizicarService.getAllKnjizicar();
	        model.addAttribute("knjizicari", knjizicari);
	        return "knjizicari";
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<Knjizicar> getKnjizicar(@PathVariable Long id) {
	        Knjizicar knjizicar = knjizicarService.getKnjizicar(id);
	        if (knjizicar != null) {
	            return new ResponseEntity<>(knjizicar, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

  


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("knjizicar", new Knjizicar());
        return "create-knjizicar";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Knjizicar> createKnjizicar(@ModelAttribute Knjizicar knjizicar) {
        Knjizicar createdKnjizicar = knjizicarService.createKnjizicar(knjizicar);
        return new ResponseEntity<>(createdKnjizicar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/update")
    public String updateKnjizicarForm(@PathVariable Long id, Model model) {
        Knjizicar knjizicar = knjizicarService.getKnjizicar(id);
        if (knjizicar != null) {
            model.addAttribute("knjizicar", knjizicar);
            return "update-knjizicar";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/{id}/update")
    public ResponseEntity<Knjizicar> updateKnjizicar(@PathVariable Long id, @ModelAttribute Knjizicar knjizicarData) {
        Knjizicar updateKnjizicar = knjizicarService.updateKnjizicar(id, knjizicarData);
        if (updateKnjizicar != null) {
            return new ResponseEntity<>(updateKnjizicar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   

    @GetMapping("/{id}/delete")
    public String deleteKnjizicarForm(@PathVariable Long id, Model model) {
        Knjizicar knjizicar = knjizicarService.getKnjizicar(id);
        if (knjizicar != null) {
            model.addAttribute("knjizicar", knjizicar);
            return "delete-knjizicar";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteKnjizicar(@PathVariable Long id,Model model) {
        knjizicarService.deleteKnjizicar(id);
        Iterable<Knjizicar> knjizicar = knjizicarService.getAllKnjizicar();
        model.addAttribute("knjizicar", knjizicar);
        return "knjizicari";
    }

}

