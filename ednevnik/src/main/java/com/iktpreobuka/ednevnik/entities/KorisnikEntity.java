package com.iktpreobuka.ednevnik.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Size(min=5, max=10, message = "Korisničko ime mora biti između {min} i {max} karaktera dugacko.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Korisničko ime može sadržati samo slova i brojeve.")
	private String korisnickoIme;
	
	@Column(name = "sifra")
	@NotNull(message = "Password must be provided.")
	@Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.")
	private String sifra;
	
	@Column(name = "aktivno")
	private boolean aktivno;
	
	@Version
	private Integer version;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;
	
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
		return "KorisnikEntity [id=" + id + ", korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", aktivno="
				+ aktivno + ", version=" + version + "]";
	}

	
}