package com.example.knjiznica.model;

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

    
    
	public StudentKnjiga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentKnjiga(Long id, Student student, Knjiga knjiga) {
		super();
		this.id = id;
		this.student = student;
		this.knjiga = knjiga;
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

	  
}
