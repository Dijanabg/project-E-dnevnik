package com.iktpreobuka.ednevnik.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ocena")
public class OcenaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ocena_id")
	private Integer id;
	
	@Column(name = "vrednost_ocene")
	@JsonView(Views.Public.class)
	private Integer vrednostOcene;
	
	@Column(name = "datum")
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	private Date datum;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	private EAktivnostEntity aktivnost;

	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	private EPolugodisteEntity semestar;
	
	@Version
	private Integer version;
	
	//nastavnik koji daje ocenu
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ocenjivac")
	private NastavnikEntity ocenjivac;
	
	//ucenik koji dobija ocenu
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn	(name = "ucenik")
	private UcenikEntity ucenik;


	public OcenaEntity() {
		super();
	}


	public OcenaEntity(Integer id, Integer vrednostOcene, Date datum, EAktivnostEntity aktivnost,
			EPolugodisteEntity semestar, Integer version, NastavnikEntity ocenjivac, UcenikEntity ucenik) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.semestar = semestar;
		this.version = version;
		this.ocenjivac = ocenjivac;
		this.ucenik = ucenik;
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


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
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


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public NastavnikEntity getOcenjivac() {
		return ocenjivac;
	}


	public void setOcenjivac(NastavnikEntity ocenjivac) {
		this.ocenjivac = ocenjivac;
	}


	public UcenikEntity getUcenik() {
		return ucenik;
	}


	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}


	@Override
	public String toString() {
		return "OcenaEntity [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", semestar=" + semestar + ", version=" + version + ", ocenjivac=" + ocenjivac
				+ ", ucenik=" + ucenik + "]";
	}

	
}
