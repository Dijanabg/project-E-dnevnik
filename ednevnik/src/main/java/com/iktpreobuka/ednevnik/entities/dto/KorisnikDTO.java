package com.iktpreobuka.ednevnik.entities.dto;

import com.iktpreobuka.ednevnik.entities.RoleEntity;

public class KorisnikDTO {
	private Integer id;
    private String korisnickoIme;
    private RoleEntity rola;
    
	public KorisnikDTO() {
		super();
	}
	
	public KorisnikDTO(Integer id, String korisnickoIme, RoleEntity rola) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.rola = rola;
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
	public RoleEntity getRola() {
		return rola;
	}
	public void setRola(RoleEntity rola) {
		this.rola = rola;
	}
	@Override
	public String toString() {
		return "KorisnikDTO [id=" + id + ", korisnickoIme=" + korisnickoIme + ", rola=" + rola + "]";
	}
    
    
}
