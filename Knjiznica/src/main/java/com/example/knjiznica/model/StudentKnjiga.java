package com.example.knjiznica.model;

import java.time.LocalDate;

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
@Table(name = "StudentKnjiga")
public class StudentKnjiga {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "KnjigaId")
    private Knjiga knjiga;
    
    @Column(name = "DatumIzdavanja")
    private LocalDate datumIzdavanja;

    @Column(name = "DatumVracanja")
    private LocalDate datumVracanja;

    @Column(name = "ObavijestPoslana")
    private Boolean obavijestPoslana;
    
    
	public StudentKnjiga() {
		super();
		// TODO Auto-generated constructor stub
	}


	public StudentKnjiga(Long id, Student student, Knjiga knjiga, LocalDate datumIzdavanja, LocalDate datumVracanja,
			boolean obavijestPoslana) {
		super();
		this.id = id;
		this.student = student;
		this.knjiga = knjiga;
		this.datumIzdavanja = datumIzdavanja;
		this.datumVracanja = datumVracanja;
		this.obavijestPoslana = obavijestPoslana;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Knjiga getKnjiga() {
		return knjiga;
	}


	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}


	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}


	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}


	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}


	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}


	public boolean isObavijestPoslana() {
		return obavijestPoslana;
	}


	public void setObavijestPoslana(boolean obavijestPoslana) {
		this.obavijestPoslana = obavijestPoslana;
	}

	

	  
}
