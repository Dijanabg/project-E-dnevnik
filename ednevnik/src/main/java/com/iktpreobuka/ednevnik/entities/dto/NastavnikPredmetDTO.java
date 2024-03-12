package com.iktpreobuka.ednevnik.entities.dto;

public class NastavnikPredmetDTO {
	private Integer id;
    private Integer nastavnikId;
    private String nastavnikIme; 
    private Integer predmetId;
    private String predmetNaziv; 
    
	public NastavnikPredmetDTO() {
		super();
	}

	public NastavnikPredmetDTO(Integer id, Integer nastavnikId, String nastavnikIme, Integer predmetId,
			String predmetNaziv) {
		super();
		this.id = id;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
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
		return "NastavnikPredmetDTO [id=" + id + ", nastavnikId=" + nastavnikId + ", nastavnikIme=" + nastavnikIme
				+ ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv  + "]";
	}
    
    
}
