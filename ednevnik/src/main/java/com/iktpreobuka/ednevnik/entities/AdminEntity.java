package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "admin")
public class AdminEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private KorisnikEntity korisnik;
    
    @Column(name = "ime")
	@NotNull(message = "Ime mora biti uneto.")
	private String ime;

	@Column(name = "prezime")
	@NotNull(message = "Prezime mora biti uneto.")
	private String prezime;
	
	public AdminEntity() {
		super();
	}

	public AdminEntity(Integer id, KorisnikEntity korisnik, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikEntity korisnik) {
		this.korisnik = korisnik;
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

	@Override
	public String toString() {
		return "AdminEntity [id=" + id + ", korisnik=" + korisnik + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

	
}
