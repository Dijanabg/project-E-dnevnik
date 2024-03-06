package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

public class RazredDTO {
	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonView(Views.Public.class)
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
