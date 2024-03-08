package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PredmetDTO {
	private Integer id;
    
	@NotBlank(message = "Naziv predmeta mora biti unet.")
	private String nazivPredmeta;
    
	@NotNull(message = "Fond casova mora biti unet.")
	private Integer casovaNedeljno;
    
	private String epolugodiste; //  enumeracija se u DTO prenosi kao String za jednostavnost
    
	private Integer version;
    
	public PredmetDTO() {
		super();
	}

	public PredmetDTO(Integer id, String nazivPredmeta, Integer casovaNedeljno, String epolugodiste, Integer version) {
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

	public String getEpolugodiste() {
		return epolugodiste;
	}

	public void setEpolugodiste(String epolugodiste) {
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
		return "PredmetDTO [id=" + id + ", nazivPredmeta=" + nazivPredmeta + ", casovaNedeljno=" + casovaNedeljno
				+ ", epolugodiste=" + epolugodiste + ", version=" + version + "]";
	}

}
