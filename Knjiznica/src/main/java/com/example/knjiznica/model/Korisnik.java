package com.example.knjiznica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "Korisnik")
public class Korisnik {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
    @Column(name = "Email")
    private String email;
    

    @Column(name = "Lozinka")
    private String lozinka;
    
	@ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "KnjizicarId")
    private Knjizicar knjizicar;
    
    @Column(name = "Vrsta_korisnika")
    private String vrstaKorisnika;


	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Korisnik(Long id, String email, String lozinka, Student student, Knjizicar knjizicar,
			String vrstaKorisnika) {
		super();
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.student = student;
		this.knjizicar = knjizicar;
		this.vrstaKorisnika = vrstaKorisnika;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Knjizicar getKnjizicar() {
		return knjizicar;
	}


	public void setKnjizicar(Knjizicar knjizicar) {
		this.knjizicar = knjizicar;
	}


	public String getVrstaKorisnika() {
		return vrstaKorisnika;
	}


	public void setVrstaKorisnika(String vrstaKorisnika) {
		this.vrstaKorisnika = vrstaKorisnika;
	}


	


	
}
