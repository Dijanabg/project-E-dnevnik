package com.iktpreobuka.ednevnik.entities.dto;

public class UcenikDTO {
	private Integer id;
    private String ime;
    private String prezime;
    private String email;
    private KorisnikDTO korisnik;
	public UcenikDTO() {
		super();
	}
	public UcenikDTO(Integer id, String ime, String prezime, String email, KorisnikDTO korisnik) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.korisnik = korisnik;
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
	public KorisnikDTO getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	@Override
	public String toString() {
		return "UcenikDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", korisnik="
				+ korisnik + "]";
	}
	
    
}
