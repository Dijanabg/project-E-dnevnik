package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleDTO {
	
	private Integer id;
	
	@NotBlank(message = "Rola ne moze biti prazno polje")
	@Size(min = 3, max = 20, message = "Ime role mora biti između {min} i {max} karaktera.")
	@JsonView(Views.Admin.class)
	private String name;
	
	public RoleDTO() {
		super();
	}
	
	public RoleDTO(Integer id,
			@NotBlank(message = "Rola ne moze biti prazno polje") @Size(min = 3, max = 20, message = "Ime role mora biti između {min} i {max} karaktera.") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RoleDTO [id=" + id + ", name=" + name + "]";
	}
    
    
}
