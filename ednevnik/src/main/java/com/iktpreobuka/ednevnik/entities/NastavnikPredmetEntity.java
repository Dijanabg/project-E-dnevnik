package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

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
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Table(name = "nastavnikPredmet")
@Table(name = "nastavnikPredmet", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"nastavnik_id", "predmet_id"})
	})
public class NastavnikPredmetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik")
	@JsonView(Views.Public.class)
	private NastavnikEntity nastavnik;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet")
	@JsonView(Views.Public.class)
	private PredmetEntity predmet;
	
	public NastavnikPredmetEntity() {
		super();
	}

	public NastavnikPredmetEntity(Integer id, Integer version, NastavnikEntity nastavnik, PredmetEntity predmet) {
		super();
		this.id = id;
		this.version = version;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
	    return "NastavnikPredmetEntity{" +
	            "id=" + id +
	            ", nastavnikId=" + nastavnik.getId() + // Samo ID umesto celog objekta
	            ", predmetId=" + predmet.getId() + // Samo ID umesto celog objekta
	            '}';
	}

	
}