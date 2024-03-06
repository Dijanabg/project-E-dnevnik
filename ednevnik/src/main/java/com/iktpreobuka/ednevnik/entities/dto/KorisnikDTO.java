package com.iktpreobuka.ednevnik.entities.dto;

public class KorisnikDTO {
	private Integer id;
    private String korisnickoIme;
    private String sifra;
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
