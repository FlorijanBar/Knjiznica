package com.example.knjiznica.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder

@Entity
@Table(name="Knjiga")
public class Knjiga {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="Naziv")
	private String naziv;
	
	@Column(name="Autor")
	private String autor;
	@Column(name="Godina_izdanja")
	private int godina_izdanja;
	@Column(name="Broj_stranica")
	private int broj_stranica;
	@Column(name="Oznaka")
	private String oznaka;
	@Column(name="Inventurni_broj")
	private int inventurni_broj;
	
	
	
	public Knjiga() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Knjiga(Long id, String naziv, String autor, int godina_izdanja, int broj_stranica, String oznaka,
			int inventurni_broj) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.autor = autor;
		this.godina_izdanja = godina_izdanja;
		this.broj_stranica = broj_stranica;
		this.oznaka = oznaka;
		this.inventurni_broj = inventurni_broj;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getGodina_izdanja() {
		return godina_izdanja;
	}
	public void setGodina_izdanja(int godina_izdanja) {
		this.godina_izdanja = godina_izdanja;
	}
	public int getBroj_stranica() {
		return broj_stranica;
	}
	public void setBroj_stranica(int broj_stranica) {
		this.broj_stranica = broj_stranica;
	}
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	public int getInventurni_broj() {
		return inventurni_broj;
	}
	public void setInventurni_broj(int inventurni_broj) {
		this.inventurni_broj = inventurni_broj;
	}
	
}