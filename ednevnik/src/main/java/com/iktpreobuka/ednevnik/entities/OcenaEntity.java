package com.iktpreobuka.ednevnik.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ocena")
public class OcenaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ocena_id")
	private Integer id;
	
	@Column(name = "vrednost_ocene")
	@NotNull(message = "Morate uneti ocenu.")
	@Size(min=5, max=10, message = "Vrednost ocene mora biti izmedju {min} i {max} karaktera duga.")
	private Integer vrednostOcene;
	
	@Column(name = "datum")
	private LocalDate datum;
	
	@Enumerated(EnumType.STRING)
	private EAktivnostEntity aktivnost;

	@Enumerated(EnumType.STRING)
	private EPolugodisteEntity semestar;
	
	@Version
	private Integer verzija;

	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OcenaEntity(Integer id, Integer vrednostOcene, LocalDate datum, EAktivnostEntity aktivnost,
			EPolugodisteEntity semestar, Integer verzija) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.semestar = semestar;
		this.verzija = verzija;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVrednostOcene() {
		return vrednostOcene;
	}

	public void setVrednostOcene(Integer vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public EAktivnostEntity getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(EAktivnostEntity aktivnost) {
		this.aktivnost = aktivnost;
	}

	public EPolugodisteEntity getSemestar() {
		return semestar;
	}

	public void setSemestar(EPolugodisteEntity semestar) {
		this.semestar = semestar;
	}

	public Integer getVerzija() {
		return verzija;
	}

	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}

	@Override
	public String toString() {
		return "OcenaEntity [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", semestar=" + semestar + ", verzija=" + verzija + "]";
	}
	
}
