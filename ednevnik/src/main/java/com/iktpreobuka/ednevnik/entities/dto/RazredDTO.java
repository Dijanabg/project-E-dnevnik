package com.iktpreobuka.ednevnik.entities.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RazredDTO {
	
	private Integer id;
	
	@Min(value = 1, message = "Broj razreda moze biti najmanje {value}")
	@Max(value = 8, message = "Broj razreda moze biti najvise {value}")
	@NotNull(message = "Razred mora biti unet.")
	@JsonView(Views.Public.class)
	private Integer razred;
	
	@NotNull(message = "Školska godina mora biti uneta.")
	@JsonView(Views.Public.class)
    private Integer skolskaGodinaId;
	
	public RazredDTO() {
		super();
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

	public Integer getSkolskaGodinaId() {
		return skolskaGodinaId;
	}

	public void setSkolskaGodinaId(Integer skolskaGodinaId) {
		this.skolskaGodinaId = skolskaGodinaId;
	}

	@Override
	public String toString() {
		return "RazredDTO [id=" + id + ", razred=" + razred + ", skolskaGodinaId=" + skolskaGodinaId + "]";
	}
	
}
