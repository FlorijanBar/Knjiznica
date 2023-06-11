package com.example.knjiznica.model;

import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Ime")
	private String ime;

	@Column(name = "Prezime")
	private String prezime;

	@Column(name = "Studij")
	private String studij;

	@Column(name = "Godina_studija")
	private int godinaStudija;
	
	 @Column(name = "Email")
	 private String email;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentKnjiga> izdateKnjige;

	public Student() {
		super();
	}

	

	public Student(Long id, String ime, String prezime, String studij, int godinaStudija, String email,
			List<StudentKnjiga> izdateKnjige) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.studij = studij;
		this.godinaStudija = godinaStudija;
		this.email = email;
		this.izdateKnjige = izdateKnjige;
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

	public String getStudij() {
		return studij;
	}

	public void setStudij(String studij) {
		this.studij = studij;
	}

	public int getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public List<StudentKnjiga> getIzdateKnjige() {
		return izdateKnjige;
	}

	public void setIzdateKnjige(List<StudentKnjiga> izdateKnjige) {
		this.izdateKnjige = izdateKnjige;
	}



}
