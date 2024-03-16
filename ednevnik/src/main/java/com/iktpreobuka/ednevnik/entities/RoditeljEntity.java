package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roditelj")
public class RoditeljEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roditelj_id")
	@JsonView(Views.Admin.class)
    private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id", unique = true)
	@JsonView(Views.Admin.class)
    private KorisnikEntity korisnikRoditelj;
    
    @Column(name = "ime")
    @JsonView(Views.Private.class)
	private String ime;

	@Column(name = "prezime")
	@JsonView(Views.Private.class)
	private String prezime;
	
	@Column(name = "email", unique = true)
	@JsonView(Views.Private.class)
	private String email;
	
	@Version
	protected Integer version;
	
	//deca kojima je roditelj
	@JsonBackReference
	//@JsonIgnore
	@JsonView(Views.Private.class)
	@OneToMany(mappedBy = "roditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<UcenikEntity> dete = new ArrayList<>();

	public RoditeljEntity() {
		super();
	}

	public RoditeljEntity(Integer id, KorisnikEntity korisnikRoditelj, String ime, String prezime, String email,
			Integer version, List<UcenikEntity> dete) {
		super();
		this.id = id;
		this.korisnikRoditelj = korisnikRoditelj;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.version = version;
		this.dete = dete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnikRoditelj() {
		return korisnikRoditelj;
	}

	public void setKorisnikRoditelj(KorisnikEntity korisnikRoditelj) {
		this.korisnikRoditelj = korisnikRoditelj;
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

	public List<UcenikEntity> getDete() {
		return dete;
	}

	public void setDete(List<UcenikEntity> dete) {
		this.dete = dete;
	}

	@Override
	public String toString() {
		return "RoditeljEntity [id=" + id + ", korisnikRoditelj=" + korisnikRoditelj + ", ime=" + ime + ", prezime="
				+ prezime + ", email=" + email + ", version=" + version + ", dete=" + dete + "]";
	}

	

	
}
