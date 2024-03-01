package com.iktpreobuka.ednevnik.entities;

import com.iktpreobuka.ednevnik.entities.enums.ERoleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//dodaje podklase kao tabele
@Table(name = "korisnik")
public class KorisnikEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "korisnik_id")
	private Integer id;
	
	
	@Column(name = "korisnicko_ime", unique = true)
	@NotBlank(message = "Korisnicko ime ne moze biti prazno polje")
	private String korisnickoIme;

	@Column(name = "email", unique = true)
	@NotNull(message = "Email must be provided.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	private String email;
	
	@Column(name = "sifra")
	@NotNull(message = "Password must be provided.")
	@Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.")
	private String sifra;
	
	@Column(name = "rola")
	@NotNull(message = "Uloga(rola) mora biti dodeljena.")
	@Enumerated(EnumType.STRING)
	private ERoleEntity rola;
	
	@Column(name = "ime")
	@NotNull(message = "Ime mora biti uneto.")
	private String ime;

	@Column(name = "prezime")
	@NotNull(message = "Prezime mora biti uneto.")
	private String prezime;

	@Column(name = "aktivno")
	
	private boolean aktivno;
	
	@Version
	private Integer version;

	public KorisnikEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public ERoleEntity getRola() {
		return rola;
	}

	public void setRola(ERoleEntity rola) {
		this.rola = rola;
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

	public boolean isAktivno() {
		return aktivno;
	}

	public void setAktivno(boolean aktivno) {
		this.aktivno = aktivno;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "KorisnikEntity [id=" + id + ", korisnickoIme=" + korisnickoIme + ", email=" + email + ", sifra=" + sifra
				+ ", rola=" + rola + ", ime=" + ime + ", prezime=" + prezime + ", aktivno=" + aktivno + ", version="
				+ version + "]";
	}
}