package com.iktpreobuka.ednevnik.entities.dto;

public class UcenikDTO {
	private Integer id;
    private String ime;
    private String prezime;
    private String email;
    private Integer korisnikId;
    private Integer roditeljId;
    private Integer odelenjeId;
	public UcenikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UcenikDTO(Integer id, String ime, String prezime, String email, Integer korisnikId, Integer roditeljId,
			Integer odelenjeId) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.korisnikId = korisnikId;
		this.roditeljId = roditeljId;
		this.odelenjeId = odelenjeId;
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
	public Integer getRoditeljId() {
		return roditeljId;
	}
	public void setRoditeljId(Integer roditeljId) {
		this.roditeljId = roditeljId;
	}
	public Integer getOdelenjeId() {
		return odelenjeId;
	}
	public void setOdelenjeId(Integer odelenjeId) {
		this.odelenjeId = odelenjeId;
	}
	@Override
	public String toString() {
		return "UcenikDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", korisnikId="
				+ korisnikId + ", roditeljId=" + roditeljId + ", odelenjeId=" + odelenjeId + "]";
	}
    
    
}
