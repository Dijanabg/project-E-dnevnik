package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "predmet")
public class PredmetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "predmet_id")
	private Integer id;
	
	@Column(name = "naziv_predmeta")
	@NotBlank(message = "Naziv predmeta mora biti unet.")
	private String nazivPredmeta;
	
	@Column(name = "fond")
	@NotNull(message = "Fond casova mora biti unet.")
	private Integer casovaNedeljno;
	
	@Column(name = "polugodiste")
	private EPolugodisteEntity epolugodiste;
	
	@Column(name = "verzija")
	@Version
	private Integer version;

	public PredmetEntity() {
		super();
	}

	public PredmetEntity(Integer id, String nazivPredmeta, Integer casovaNedeljno, EPolugodisteEntity epolugodiste,
			Integer version) {
		super();
		this.id = id;
		this.nazivPredmeta = nazivPredmeta;
		this.casovaNedeljno = casovaNedeljno;
		this.epolugodiste = epolugodiste;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public Integer getCasovaNedeljno() {
		return casovaNedeljno;
	}

	public void setCasovaNedeljno(Integer casovaNedeljno) {
		this.casovaNedeljno = casovaNedeljno;
	}

	public EPolugodisteEntity getEpolugodiste() {
		return epolugodiste;
	}

	public void setEpolugodiste(EPolugodisteEntity epolugodiste) {
		this.epolugodiste = epolugodiste;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "PredmetEntity [id=" + id + ", nazivPredmeta=" + nazivPredmeta + ", casovaNedeljno=" + casovaNedeljno
				+ ", epolugodiste=" + epolugodiste + ", version=" + version + "]";
	}
	
	
}
