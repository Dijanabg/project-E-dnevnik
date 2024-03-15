package com.iktpreobuka.ednevnik.entities.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RoditeljDTO {

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
    
    @NotEmpty(message = "Lista dece ne sme biti prazna.")
    private List<Integer> deteIds = new ArrayList<>();
    
	public RoditeljDTO() {
		super();
	}
	public RoditeljDTO(Integer id, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime,
			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			Integer korisnikId, @NotEmpty(message = "Lista dece ne sme biti prazna.") List<Integer> deteIds) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.korisnikId = korisnikId;
		this.deteIds = deteIds;
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
	public List<Integer> getDeteIds() {
		return deteIds;
	}
	public void setDeteIds(List<Integer> deteIds) {
		this.deteIds = deteIds;
	}
	@Override
	public String toString() {
		return "RoditeljDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", korisnikId="
				+ korisnikId + ", deteIds=" + deteIds + "]";
	}
	
	
	
    
}
