package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OdelenjeDTO {
	
	private Integer id;
	
	@NotNull(message = "Razred mora biti unet.")
	@JsonView(Views.Private.class)
    private Integer razred;
    
    @Min(value = 1, message = "Broj odelenja moze biti najmanje {value}")
	@Max(value = 10, message = "Broj odelenja moze biti najvise {value}")
	@NotNull(message = "Broj odelenja mora biti unet.")
    @JsonView(Views.Private.class)
	private Integer odelenje;
    
    private Integer version;
    @JsonView(Views.Private.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NastavnikDTO razredniStaresina;
    
	public OdelenjeDTO() {
		super();
	}

	public OdelenjeDTO(Integer id, @NotNull(message = "Razred mora biti unet.") Integer razred,
			@Min(value = 1, message = "Broj odelenja moze biti najmanje {value}") @Max(value = 10, message = "Broj odelenja moze biti najvise {value}") @NotNull(message = "Broj odelenja mora biti unet.") Integer odelenje,
			Integer version, NastavnikDTO razredniStaresina) {
		super();
		this.id = id;
		this.razred = razred;
		this.odelenje = odelenje;
		this.version = version;
		this.razredniStaresina = razredniStaresina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public Integer getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(Integer odelenje) {
		this.odelenje = odelenje;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public NastavnikDTO getRazredniStaresina() {
		return razredniStaresina;
	}

	public void setRazredniStaresina(NastavnikDTO razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
	}

	@Override
	public String toString() {
		return "OdelenjeDTO [id=" + id + ", razred=" + razred + ", odelenje=" + odelenje + ", version=" + version
				+ ", razredniStaresina=" + razredniStaresina + "]";
	}

	
}
