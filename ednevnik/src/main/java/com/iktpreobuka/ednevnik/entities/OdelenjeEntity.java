package com.iktpreobuka.ednevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "odelenje")
public class OdelenjeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "odelenje_id")
	private Integer id;
	
	@Column(name = "aktivno")
	private boolean aktivno = Boolean.FALSE;
	
	@Column(name = "razred")
	@Min(value = 1, message = "Broj razreda moze biti najmanje {value}")
	@Max(value = 8, message = "Broj razreda moze biti najvise {value}")
	@NotNull(message = "Razred mora biti unet.")
	private Integer razred;
	
	@Column(name = "odelenje")
	@Min(value = 1, message = "Broj odelenja moze biti najmanje {value}")
	@Max(value = 10, message = "Broj odelenja moze biti najvise {value}")
	@NotNull(message = "Broj odelenja mora biti unet.")
	private Integer odelenje;
	
	@Column(name = "skolska_godina")
	@NotNull(message = "Skolska godina mora biti unesena.")
	@Min(value = 2000, message = "Godiste generacije mora biti vece od {value}.")
	@Max(value = 2100, message = "Godiste generacije mora biti manje od  {value}.")
	private SkolskaGodinaEntity skolskaGodina;

	@Version
	@Column(name = "verzija")
	protected Integer verzija;

	public OdelenjeEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAktivno() {
		return aktivno;
	}

	public void setAktivno(boolean aktivno) {
		this.aktivno = aktivno;
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

	
	public SkolskaGodinaEntity getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(SkolskaGodinaEntity skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public Integer getVerzija() {
		return verzija;
	}

	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}

	@Override
	public String toString() {
		return "OdelenjeEntity [id=" + id + ", aktivno=" + aktivno + ", razred=" + razred + ", odelenje=" + odelenje
				+ ", skolskaGodina=" + skolskaGodina + ", verzija=" + verzija + "]";
	}

	
	
}
