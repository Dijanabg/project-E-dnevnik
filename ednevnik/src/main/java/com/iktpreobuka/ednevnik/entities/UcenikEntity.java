package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ucenik")
public class UcenikEntity extends KorisnikEntity{

	public UcenikEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UcenikEntity []";
	}

}