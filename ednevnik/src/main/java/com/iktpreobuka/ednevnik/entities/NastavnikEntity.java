package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnik")
public class NastavnikEntity extends KorisnikEntity{

	@JsonBackReference
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikPredmetEntity> predajePredmet = new ArrayList<>();
	
	public List<NastavnikPredmetEntity> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(List<NastavnikPredmetEntity> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public NastavnikEntity() {
		super();
	}

}

