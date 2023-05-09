package com.example.knjiznica.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Korisnik {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "Email")
    private String email;
    

    @Column(name = "Lozinka")
    private String lozinka;
    
    @Column(name = "Vrsta_korisnika")
    private String vrstaKorisnika;

	@ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "KnjizicarId")
    private Knjizicar knjizicar;

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(Long id, String ime, String prezime, String email, String lozinka, String vrstaKorisnika,
			Student student, Knjizicar knjizicar) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.vrstaKorisnika = vrstaKorisnika;
		this.student = student;
		this.knjizicar = knjizicar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

	public String getVrstaKorisnika() {
		return vrstaKorisnika;
	}

	public void setVrstaKorisnika(String vrstaKorisnika) {
		this.vrstaKorisnika = vrstaKorisnika;
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

    


	
}
