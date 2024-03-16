package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "korisnik")
public class KorisnikEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "korisnik_id")
	@JsonView(Views.Admin.class)
	private Integer id;
	
	
	@Column(name = "korisnicko_ime", unique = true)
	@JsonView(Views.Admin.class)
	private String korisnickoIme;
	
	@Column(name = "sifra")
	@JsonIgnore
	private String sifra;
	
	@Column(name = "aktivno")
	@JsonView(Views.Admin.class)
	private boolean aktivno;
	
	@Version
	private Integer version;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id") 
	@JsonView(Views.Admin.class)
	private RoleEntity role;
	
	@OneToMany(mappedBy = "korisnikUcenik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<UcenikEntity> ucenici = new ArrayList<>();

	@OneToMany(mappedBy = "korisnikNastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<NastavnikEntity> nastavnici = new ArrayList<>();

	@OneToMany(mappedBy = "korisnikRoditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<RoditeljEntity> roditelji = new ArrayList<>();

	@OneToMany(mappedBy = "korisnikAdmin", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<AdminEntity> administratori = new ArrayList<>();

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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "KorisnikEntity [id=" + id + ", korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", aktivno="
				+ aktivno + ", version=" + version + "]";
	}

	
}