package com.iktpreobuka.ednevnik.entities.dto;

import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;

public class NastavnikPredmetDTO {
	private Integer id;
    private Integer nastavnikId;
    private String nastavnikIme; // ??
    private Integer predmetId;
    private String predmetNaziv; // ??
    private Integer skolskaGodinaId;
    private String skolskaGodinaOznaka; // ??
    private EPolugodisteEntity polugodiste;
    
	public NastavnikPredmetDTO() {
		super();
	}

	public NastavnikPredmetDTO(Integer id, Integer nastavnikId, String nastavnikIme, Integer predmetId,
			String predmetNaziv, Integer skolskaGodinaId, String skolskaGodinaOznaka, EPolugodisteEntity polugodiste) {
		super();
		this.id = id;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.skolskaGodinaId = skolskaGodinaId;
		this.skolskaGodinaOznaka = skolskaGodinaOznaka;
		this.polugodiste = polugodiste;
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

	public Integer getSkolskaGodinaId() {
		return skolskaGodinaId;
	}

	public void setSkolskaGodinaId(Integer skolskaGodinaId) {
		this.skolskaGodinaId = skolskaGodinaId;
	}

	public String getSkolskaGodinaOznaka() {
		return skolskaGodinaOznaka;
	}

	public void setSkolskaGodinaOznaka(String skolskaGodinaOznaka) {
		this.skolskaGodinaOznaka = skolskaGodinaOznaka;
	}

	public EPolugodisteEntity getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(EPolugodisteEntity polugodiste) {
		this.polugodiste = polugodiste;
	}

	@Override
	public String toString() {
		return "NastavnikPredmetDTO [id=" + id + ", nastavnikId=" + nastavnikId + ", nastavnikIme=" + nastavnikIme
				+ ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv + ", skolskaGodinaId=" + skolskaGodinaId
				+ ", skolskaGodinaOznaka=" + skolskaGodinaOznaka + ", polugodiste=" + polugodiste + "]";
	}
    
    
}
