package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnikPredmet")
public class NastavnikPredmetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik")
	private NastavnikEntity nastavnik;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet")
	private PredmetEntity predmet;

	public NastavnikPredmetEntity() {
		super();
	}

	public NastavnikPredmetEntity(Integer id, NastavnikEntity nastavnik, PredmetEntity predmet) {
		super();
		this.id = id;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	@Override
	public String toString() {
		return "NastavnikPredmetEntity [id=" + id + ", nastavnik=" + nastavnik + ", predmet=" + predmet + "]";
	}
	
	
}
