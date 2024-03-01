package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "skolska_godina")
public class SkolskaGodinaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skolgod_id")
	private Integer id;
	
	@Column(name = "oznaka")
	@NotBlank(message = "Oznaka skolske godine ne moze biti prazno polje")
	private String oznaka;
	
	@OneToMany(mappedBy = "skolskaGodina")
    @JsonIgnore // da se izbegne potencijalna beskonačna rekurzija prilikom serijalizacije u JSON
    private List<OdelenjeEntity> odeljenja;
	
	@OneToMany(mappedBy = "skolskaGodina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NastavnikPredmetEntity> nastavnikPredmeti = new ArrayList<>();

	public SkolskaGodinaEntity() {
		super();
	}

	public SkolskaGodinaEntity(Integer id,
			@NotBlank(message = "Oznaka skolske godine ne moze biti prazno polje") String oznaka) {
		super();
		this.id = id;
		this.oznaka = oznaka;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<OdelenjeEntity> getOdeljenja() {
		return odeljenja;
	}

	public void setOdeljenja(List<OdelenjeEntity> odeljenja) {
		this.odeljenja = odeljenja;
	}

	@Override
	public String toString() {
		return "SkolskaGodinaEntity [id=" + id + ", oznaka=" + oznaka + ", odeljenja=" + odeljenja + "]";
	}
	
}
