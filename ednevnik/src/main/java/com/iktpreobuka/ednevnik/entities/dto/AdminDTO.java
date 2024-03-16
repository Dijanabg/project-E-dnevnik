package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.NotNull;

public class AdminDTO {

	@JsonView(Views.Admin.class)
	private Integer id;
    

	@JsonView(Views.Admin.class)
	@NotNull(message = "Ime mora biti uneto.")
	private String ime;
    

	@JsonView(Views.Admin.class)
	@NotNull(message = "Prezime mora biti uneto.")
	private String prezime;
	

	@JsonView(Views.Admin.class)
	private Integer korisnikId;
	
	public AdminDTO() {
		super();
	}

	public AdminDTO(Integer id, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime, Integer korisnikId) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnikId = korisnikId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	@Override
	public String toString() {
		return "AdminDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", korisnikId=" + korisnikId + "]";
	}

	
}
