package com.example.model;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

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

	    @ManyToOne
	    @JoinColumn(name = "KnjizicarId")
	    private Knjizicar knjizicar;
	    
	    

		public Student() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		public Student(Long id, String ime, String prezime, String studij, int godinaStudija, Knjizicar knjizicar) {
			super();
			this.id = id;
			this.ime = ime;
			this.prezime = prezime;
			this.studij = studij;
			this.godinaStudija = godinaStudija;
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

		public Knjizicar getKnjizicar() {
			return knjizicar;
		}

		public void setKnjizicar(Knjizicar knjizicar) {
			this.knjizicar = knjizicar;
		}
	    
	    
}
