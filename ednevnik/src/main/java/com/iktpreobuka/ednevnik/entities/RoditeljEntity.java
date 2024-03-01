package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roditelj")
public class RoditeljEntity extends KorisnikEntity{

	@JsonBackReference
	//@JsonIgnore
	@OneToMany(mappedBy = "roditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<UcenikEntity> dete = new ArrayList<>();
	
	public RoditeljEntity() {
		super();
	}

	@Override
	public String toString() {
		return "RoditeljEntity []";
	}

}
