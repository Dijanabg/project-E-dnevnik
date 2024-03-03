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
	
	@Column(name = "sifra")
	@NotNull(message = "Password must be provided.")
	@Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.")
	private String sifra;
	
	@Column(name = "rola")
	@NotNull(message = "Uloga(rola) mora biti dodeljena.")
	@Enumerated(EnumType.STRING)
	private ERoleEntity rola;
	
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
		return "KorisnikEntity [id=" + id + ", korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", rola=" + rola
				+ ", aktivno=" + aktivno + ", version=" + version + "]";
	}

	
}