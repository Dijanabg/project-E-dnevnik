package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

public class NastavnikOdelenjeDTO {
	@JsonView(Views.Admin.class)
	private Integer id;
	@JsonView(Views.Admin.class)
    private Integer nastavnikId;
	@JsonView(Views.Private.class)
    private String nastavnikIme; 
    @JsonIgnore
    @JsonView(Views.Private.class)
    private String nastavnikPrezime;
    @JsonView(Views.Admin.class)
    private Integer odeljenjeId;
    @JsonView(Views.Admin.class)
    private Integer predmetId;
    @JsonView(Views.Private.class)
    private String predmetNaziv;
    
	public NastavnikOdelenjeDTO() {
		super();
	}

	public NastavnikOdelenjeDTO(Integer id, Integer nastavnikId, String nastavnikIme, String nastavnikPrezime,
			Integer odeljenjeId, Integer predmetId, String predmetNaziv) {
		super();
		this.id = id;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
		this.nastavnikPrezime = nastavnikPrezime;
		this.odeljenjeId = odeljenjeId;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNastavnikId() {
		return nastavnikId;
	}

	public void setNastavnikId(Integer nastavnikId) {
		this.nastavnikId = nastavnikId;
	}

	public String getNastavnikIme() {
		return nastavnikIme;
	}

	public void setNastavnikIme(String nastavnikIme) {
		this.nastavnikIme = nastavnikIme;
	}

	public String getNastavnikPrezime() {
		return nastavnikPrezime;
	}

	public void setNastavnikPrezime(String nastavnikPrezime) {
		this.nastavnikPrezime = nastavnikPrezime;
	}

	public Integer getOdeljenjeId() {
		return odeljenjeId;
	}

	public void setOdeljenjeId(Integer odeljenjeId) {
		this.odeljenjeId = odeljenjeId;
	}

	public Integer getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(Integer predmetId) {
		this.predmetId = predmetId;
	}

	public String getPredmetNaziv() {
		return predmetNaziv;
	}

	public void setPredmetNaziv(String predmetNaziv) {
		this.predmetNaziv = predmetNaziv;
	}

	@Override
	public String toString() {
		return "NastavnikOdelenjeDTO [id=" + id + ", nastavnikId=" + nastavnikId + ", nastavnikIme=" + nastavnikIme
				+ ", nastavnikPrezime=" + nastavnikPrezime + ", odeljenjeId=" + odeljenjeId + ", predmetId=" + predmetId
				+ ", predmetNaziv=" + predmetNaziv + "]";
	}
}
