package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
	
	private Integer id;
	
	@NotBlank(message = "Rola ne moze biti prazno polje")
	private String name;
	
	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleDTO(Integer id, String name) {
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
