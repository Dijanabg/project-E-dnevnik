package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PredmetDTO {
	private Integer id;
    
	@NotBlank(message = "Naziv predmeta mora biti unet.")
	private String nazivPredmeta;
    
	@NotNull(message = "Fond casova mora biti unet.")
	private Integer casovaNedeljno;
   
	private Integer version;
    
	@NotNull(message = "Razred mora biti unet.")
	private Integer razredId;
	
	public PredmetDTO() {
		super();
	}

	public PredmetDTO(Integer id, @NotBlank(message = "Naziv predmeta mora biti unet.") String nazivPredmeta,
			@NotNull(message = "Fond casova mora biti unet.") Integer casovaNedeljno, Integer version,
			Integer razredId) {
		super();
		this.id = id;
		this.nazivPredmeta = nazivPredmeta;
		this.casovaNedeljno = casovaNedeljno;
		this.version = version;
		this.razredId = razredId;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getRazredId() {
		return razredId;
	}

	public void setRazredId(Integer razredId) {
		this.razredId = razredId;
	}

	@Override
	public String toString() {
		return "PredmetDTO [id=" + id + ", nazivPredmeta=" + nazivPredmeta + ", casovaNedeljno=" + casovaNedeljno
				+ ", version=" + version + ", razredId=" + razredId + "]";
	}

	
}
