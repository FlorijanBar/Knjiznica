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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.knjiznica.model.Knjizicar;
import com.example.knjiznica.model.Student;
import com.example.knjiznica.service.KnjizicarService;
import com.example.knjiznica.service.StudentService;
@Controller
@RequestMapping("/student")
public class StudentController {

	
	@Autowired
    private StudentService studentService;
	
	
	 @GetMapping("/all")
	    public String getAllStudent(Model model) {
	        Iterable<Student> studenti = studentService.getAllStudent();
	        model.addAttribute("studenti", studenti);
	        return "studenti";
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
	        Student student = studentService.getStudent(id);
	        if (student != null) {
	            return new ResponseEntity<>(student, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

  


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView createStudent(@ModelAttribute Student student) {
        Student createdStudent = studentService.createStudent(student);
       
        if (createdStudent != null) {
           
            return new ModelAndView("redirect:/student/all");
        } else {
            
            ModelAndView modelAndView = new ModelAndView("knjiga");
            modelAndView.addObject("errorMessage", "Došlo je do pogreške prilikom stvaranja knjige.");
            return modelAndView;
        }
    }

    @GetMapping("/{id}/update")
    public String updateStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "update-student";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/{id}/update")
    public ModelAndView updateStudent(@PathVariable Long id, @ModelAttribute Student studentData) {
        Student updateStudent = studentService.updateStudent(id, studentData);
        if (updateStudent != null) {
            RedirectView redirect = new RedirectView("/student/all");
            return new ModelAndView(redirect);
        } else {
            return new ModelAndView("error-page"); // Stranica za prikaz greške
        }
    }

   

    @GetMapping("/{id}/delete")
    public String deleteStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "delete-student";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id,Model model) {
        studentService.deleteStudent(id);
        Iterable<Student> student = studentService.getAllStudent();
        model.addAttribute("student", student);
        return "studenti";
    }
	
	
}
