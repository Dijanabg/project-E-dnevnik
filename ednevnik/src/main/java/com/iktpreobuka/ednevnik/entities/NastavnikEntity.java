package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnik")
public class NastavnikEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nastavnik_id")
    private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id", unique = true)
	
    private KorisnikEntity korisnikNastavnik;
    
    @Column(name = "ime")
    @JsonView(Views.Public.class)
	private String ime;

	@Column(name = "prezime")
	@JsonView(Views.Public.class)
	private String prezime;
	
	@Column(name = "email", unique = true)
	@JsonView(Views.Public.class)
	private String email;
	
	@Version
	protected Integer version;
	
	@OneToOne(mappedBy = "razredniStaresina", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonView(Views.Public.class)
	private OdelenjeEntity odeljenjeRazrednog;
	
	//predaje predmet
	@JsonBackReference
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Public.class)
	private List<NastavnikPredmetEntity> predajePredmet = new ArrayList<>();
	
	//odelenje u kojem predaje
	@JsonView(Views.Public.class)
	@OneToMany(mappedBy = "predavac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<NastavnikOdelenjeEntity> nastavnikOdelenje = new ArrayList<>();

	public NastavnikEntity() {
		super();
	}

	public NastavnikEntity(Integer id, KorisnikEntity korisnikNastavnik, String ime, String prezime, String email,
			Integer version, OdelenjeEntity odeljenjeRazrednog, List<NastavnikPredmetEntity> predajePredmet,
			List<NastavnikOdelenjeEntity> nastavnikOdelenje) {
		super();
		this.id = id;
		this.korisnikNastavnik = korisnikNastavnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.version = version;
		this.odeljenjeRazrednog = odeljenjeRazrednog;
		this.predajePredmet = predajePredmet;
		this.nastavnikOdelenje = nastavnikOdelenje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnikNastavnik() {
		return korisnikNastavnik;
	}

	public void setKorisnikNastavnik(KorisnikEntity korisnikNastavnik) {
		this.korisnikNastavnik = korisnikNastavnik;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OdelenjeEntity getOdeljenjeRazrednog() {
		return odeljenjeRazrednog;
	}

	public void setOdeljenjeRazrednog(OdelenjeEntity odeljenjeRazrednog) {
		this.odeljenjeRazrednog = odeljenjeRazrednog;
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

	@Override
	public String toString() {
	    return "NastavnikEntity{" +
	            "id=" + id +
	            ", ime='" + ime + '\'' +
	            ", prezime='" + prezime + '\'' +
	            
	            '}';
	}

	
}

