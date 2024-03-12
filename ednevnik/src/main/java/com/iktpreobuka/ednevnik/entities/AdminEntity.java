package com.iktpreobuka.ednevnik.entities;

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
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "admin")
public class AdminEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	@JsonView(Views.Admin.class)
    private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id", unique = true)
    private KorisnikEntity korisnikAdmin;
    
    @Column(name = "ime")
    @JsonView(Views.Public.class)
	private String ime;

	@Column(name = "prezime")
	@JsonView(Views.Public.class)
	private String prezime;
	
	@Version
	private Integer version;
	
	public AdminEntity() {
		super();
	}

	public AdminEntity(Integer id, KorisnikEntity korisnikAdmin, String ime, String prezime, Integer version) {
		super();
		this.id = id;
		this.korisnikAdmin = korisnikAdmin;
		this.ime = ime;
		this.prezime = prezime;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnikAdmin() {
		return korisnikAdmin;
	}

	public void setKorisnikAdmin(KorisnikEntity korisnikAdmin) {
		this.korisnikAdmin = korisnikAdmin;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "AdminEntity [id=" + id + ", korisnikAdmin=" + korisnikAdmin + ", ime=" + ime + ", prezime=" + prezime
				+ ", version=" + version + "]";
	}

	
}
