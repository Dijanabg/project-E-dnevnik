package com.iktpreobuka.ednevnik.entities.dto;

import java.util.List;

public class NastavnikPredmetUcenikDTO {
	private Integer id;
    private Integer ucenikId;
    private String ucenikIme; // ??
    private Integer nastavnikPredmetId;
    private Integer nastavnikId; // ??
    private String nastavnikIme; // ??
    private Integer predmetId; // ??
    private String predmetNaziv; // ??
    private List<Integer> ocene; // ??
	public NastavnikPredmetUcenikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NastavnikPredmetUcenikDTO(Integer id, Integer ucenikId, String ucenikIme, Integer nastavnikPredmetId,
			Integer nastavnikId, String nastavnikIme, Integer predmetId, String predmetNaziv, List<Integer> ocene) {
		super();
		this.id = id;
		this.ucenikId = ucenikId;
		this.ucenikIme = ucenikIme;
		this.nastavnikPredmetId = nastavnikPredmetId;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.ocene = ocene;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUcenikId() {
		return ucenikId;
	}
	public void setUcenikId(Integer ucenikId) {
		this.ucenikId = ucenikId;
	}
	public String getUcenikIme() {
		return ucenikIme;
	}
	public void setUcenikIme(String ucenikIme) {
		this.ucenikIme = ucenikIme;
	}
	public Integer getNastavnikPredmetId() {
		return nastavnikPredmetId;
	}
	public void setNastavnikPredmetId(Integer nastavnikPredmetId) {
		this.nastavnikPredmetId = nastavnikPredmetId;
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
	public List<Integer> getOcene() {
		return ocene;
	}
	public void setOcene(List<Integer> ocene) {
		this.ocene = ocene;
	}
	@Override
	public String toString() {
		return "NastavnikPredmetUcenikDTO [id=" + id + ", ucenikId=" + ucenikId + ", ucenikIme=" + ucenikIme
				+ ", nastavnikPredmetId=" + nastavnikPredmetId + ", nastavnikId=" + nastavnikId + ", nastavnikIme="
				+ nastavnikIme + ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv + ", ocene=" + ocene
				+ "]";
	}
    
    
}
