package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class KorisnikDTO {
	
	
	private Integer id;
	
	@JsonView(Views.Admin.class)
	@NotBlank(message = "Korisnicko ime ne moze biti prazno polje")
	@Size(min=5, max=10, message = "Korisničko ime mora biti između {min} i {max} karaktera dugacko.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Korisničko ime može sadržati samo slova i brojeve.")
    private String korisnickoIme;
	
	@NotNull(message = "Password must be provided.")
	@Size(min=5, max=10, message = "Password must be between {min} and {max} characters long.")
	private String sifra;
	
	@JsonView(Views.Admin.class)
	@NotNull(message = "Role must be provided.")
    private Integer rolaId;
    
	public KorisnikDTO() {
		super();
	}

	
	public KorisnikDTO(Integer id, String korisnickoIme, String sifra, Integer rolaId) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.rolaId = rolaId;
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

	public Integer getRolaId() {
		return rolaId;
	}

	public void setRolaId(Integer rolaId) {
		this.rolaId = rolaId;
	}


	@Override
	public String toString() {
		return "KorisnikDTO [id=" + id + ", korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", rolaId=" + rolaId
				+ "]";
	}
	
}
