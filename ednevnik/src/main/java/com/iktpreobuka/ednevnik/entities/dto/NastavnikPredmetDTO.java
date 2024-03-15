package com.iktpreobuka.ednevnik.entities.dto;

public class NastavnikPredmetDTO {
	private Integer id;
    private Integer nastavnikId;
    private String nastavnikIme; 
    private String nastavnikPrezime;
    private Integer predmetId;
    private String predmetNaziv; 
    private Integer predmetRazred;
    
	public NastavnikPredmetDTO() {
		super();
	}

	public NastavnikPredmetDTO(Integer id, Integer nastavnikId, String nastavnikIme, String nastavnikPrezime,
			Integer predmetId, String predmetNaziv, Integer predmetRazred) {
		super();
		this.id = id;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
		this.nastavnikPrezime = nastavnikPrezime;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.predmetRazred = predmetRazred;
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

	public String getNastavnikPrezime() {
		return nastavnikPrezime;
	}

	public void setNastavnikPrezime(String nastavnikPrezime) {
		this.nastavnikPrezime = nastavnikPrezime;
	}

	public Integer getPredmetRazred() {
		return predmetRazred;
	}

	public void setPredmetRazred(Integer razredEntity) {
		this.predmetRazred = razredEntity;
	}

	@Override
	public String toString() {
		return "NastavnikPredmetDTO [id=" + id + ", nastavnikId=" + nastavnikId + ", nastavnikIme=" + nastavnikIme
				+ ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv  + "]";
	}
    
    
}
