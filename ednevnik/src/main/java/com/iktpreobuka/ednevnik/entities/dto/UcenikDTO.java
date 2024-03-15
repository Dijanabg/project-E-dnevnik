package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UcenikDTO {
	private Integer id;
	@NotNull(message = "Ime mora biti uneto.")
    private String ime;
	
	@NotNull(message = "Prezime mora biti uneto.")
    private String prezime;
	
	@NotNull(message = "Email must be provided.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
    private String email;
	
	private Integer korisnikId;
	
	private RoditeljDTO roditelj;
	
	private Integer odelenje;

	private Integer razred;
	
	public UcenikDTO() {
		super();
	}
//	public UcenikDTO(Integer id, @NotNull(message = "Ime mora biti uneto.") String ime,
//			@NotNull(message = "Prezime mora biti uneto.") String prezime,
//			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
//			Integer korisnikId) {
//		super();
//		this.id = id;
//		this.ime = ime;
//		this.prezime = prezime;
//		this.email = email;
//		this.korisnikId = korisnikId;
//	}
	
	
	public UcenikDTO(Integer id, @NotNull(message = "Ime mora biti uneto.") String ime,
		@NotNull(message = "Prezime mora biti uneto.") String prezime,
		@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
		Integer korisnikId, RoditeljDTO roditelj) {
			super();
			this.id = id;
			this.ime = ime;
			this.prezime = prezime;
			this.email = email;
			this.korisnikId = korisnikId;
			this.roditelj = roditelj;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}
	
	public RoditeljDTO getRoditelj() {
		return roditelj;
	}


	public void setRoditelj(RoditeljDTO roditelj) {
		this.roditelj = roditelj;
	}


	public Integer getRazred() {
		return razred;
	}


	public void setRazred(Integer razred) {
		this.razred = razred;
	}


	public Integer getOdelenje() {
		return odelenje;
	}


	public void setOdelenje(Integer integer) {
		this.odelenje = integer;
	}


	@Override
	public String toString() {
		return "UcenikDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", korisnikId="
				+ korisnikId + "]";
	}
	
	
}
