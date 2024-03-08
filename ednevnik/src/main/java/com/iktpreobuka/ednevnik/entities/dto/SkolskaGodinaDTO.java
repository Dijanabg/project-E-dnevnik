package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotBlank;

public class SkolskaGodinaDTO {
	private Integer id;
	
	@NotBlank(message = "Oznaka skolske godine ne moze biti prazno polje")
	private String oznaka;
	
	public SkolskaGodinaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SkolskaGodinaDTO(Integer id, String oznaka) {
		super();
		this.id = id;
		this.oznaka = oznaka;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	@Override
	public String toString() {
		return "SkolskaGodinaDTO [id=" + id + ", oznaka=" + oznaka + "]";
	}

}
