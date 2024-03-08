package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnik")
public class NastavnikEntity {
	@Id
	@Column(name = "nastavnik_id")
    private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "nastavnik_id")
    private KorisnikEntity korisnik;
    
    @Column(name = "ime")
	private String ime;

	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "email", unique = true)
	private String email;
	
	//predaje predmet
	@JsonBackReference
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikPredmetEntity> predajePredmet = new ArrayList<>();
	
	//odelenje u kojem predaje !!!!!!!!!!ne znam dal mi treba
	@OneToMany(mappedBy = "predavac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<NastavnikOdelenjeEntity> nastavnikOdelenje = new ArrayList<>();

	public NastavnikEntity() {
		super();
	}

	public NastavnikEntity(Integer id, KorisnikEntity korisnik, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime,
			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			List<NastavnikPredmetEntity> predajePredmet, List<NastavnikOdelenjeEntity> nastavnikOdelenje) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.predajePredmet = predajePredmet;
		this.nastavnikOdelenje = nastavnikOdelenje;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<NastavnikPredmetEntity> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(List<NastavnikPredmetEntity> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public List<NastavnikOdelenjeEntity> getNastavnikOdelenje() {
		return nastavnikOdelenje;
	}

	public void setNastavnikOdelenje(List<NastavnikOdelenjeEntity> nastavnikOdelenje) {
		this.nastavnikOdelenje = nastavnikOdelenje;
	}

	
	

}

