package com.iktpreobuka.ednevnik.entities.dto;

public class NastavnikDTO {
	private Integer id;
    private String ime;
    private String prezime;
    private String email;
	public NastavnikDTO() {
		super();
	}
	public NastavnikDTO(Integer id, String ime, String prezime, String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
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
	@Override
	public String toString() {
		return "NastavnikDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + "]";
	}
    
    
}
