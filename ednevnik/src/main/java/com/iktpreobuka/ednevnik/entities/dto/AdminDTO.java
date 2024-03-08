package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotNull;

public class AdminDTO {
	private Integer id;
    
	@NotNull(message = "Ime mora biti uneto.")
	private String ime;
    
	@NotNull(message = "Prezime mora biti uneto.")
	private String prezime;
    
	public AdminDTO() {
		super();
	}

	public AdminDTO(Integer id, String ime, String prezime) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
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

	@Override
	public String toString() {
		return "AdminDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + "]";
	}
    
    
}
