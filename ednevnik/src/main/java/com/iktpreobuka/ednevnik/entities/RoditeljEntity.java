package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roditelj")
public class RoditeljEntity extends KorisnikEntity{

	public RoditeljEntity() {
		super();
	}

	@Override
	public String toString() {
		return "RoditeljEntity []";
	}

}
