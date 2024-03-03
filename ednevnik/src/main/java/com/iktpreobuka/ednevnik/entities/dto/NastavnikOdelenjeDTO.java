package com.iktpreobuka.ednevnik.entities.dto;

public class NastavnikOdelenjeDTO {
	private Integer id;
    private Integer nastavnikId;
    private String nastavnikIme; // ??? ime nastavnika
    private Integer odeljenjeId;
    private String odeljenjeOznaka; // ??? oznaka odelenja
    
	public NastavnikOdelenjeDTO() {
		super();
	}

	public NastavnikOdelenjeDTO(Integer id, Integer nastavnikId, String nastavnikIme, Integer odeljenjeId,
			String odeljenjeOznaka) {
		super();
		this.id = id;
		this.nastavnikId = nastavnikId;
		this.nastavnikIme = nastavnikIme;
		this.odeljenjeId = odeljenjeId;
		this.odeljenjeOznaka = odeljenjeOznaka;
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

	public Integer getOdeljenjeId() {
		return odeljenjeId;
	}

	public void setOdeljenjeId(Integer odeljenjeId) {
		this.odeljenjeId = odeljenjeId;
	}

	public String getOdeljenjeOznaka() {
		return odeljenjeOznaka;
	}

	public void setOdeljenjeOznaka(String odeljenjeOznaka) {
		this.odeljenjeOznaka = odeljenjeOznaka;
	}

	@Override
	public String toString() {
		return "NastavnikOdelenjeDTO [id=" + id + ", nastavnikId=" + nastavnikId + ", nastavnikIme=" + nastavnikIme
				+ ", odeljenjeId=" + odeljenjeId + ", odeljenjeOznaka=" + odeljenjeOznaka + "]";
	}
    
    
}
