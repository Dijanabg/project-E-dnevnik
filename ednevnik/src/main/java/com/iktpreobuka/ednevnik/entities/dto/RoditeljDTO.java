package com.iktpreobuka.ednevnik.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class RoditeljDTO {

	private Integer id;
    private String ime;
    private String prezime;
    private String email;
    private List<Integer> deteIds = new ArrayList<>();
	public RoditeljDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoditeljDTO(Integer id, String ime, String prezime, String email, List<Integer> deteIds) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
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
	public List<Integer> getDeteIds() {
		return deteIds;
	}
	public void setDeteIds(List<Integer> deteIds) {
		this.deteIds = deteIds;
	}
	@Override
	public String toString() {
		return "RoditeljDTO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", deteIds="
				+ deteIds + "]";
	}
    
}