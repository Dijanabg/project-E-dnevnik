package com.iktpreobuka.ednevnik.entities.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RazredDTO {
	
	private Integer id;
	
	@Min(value = 1, message = "Broj razreda moze biti najmanje {value}")
	@Max(value = 8, message = "Broj razreda moze biti najvise {value}")
	@NotNull(message = "Razred mora biti unet.")
	private Integer razred;
	
	public RazredDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRazred() {
		return razred;
	}
	public void setRazred(Integer razred) {
		this.razred = razred;
	}
	
}
