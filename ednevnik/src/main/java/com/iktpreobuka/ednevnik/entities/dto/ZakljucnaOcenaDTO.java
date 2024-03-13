package com.iktpreobuka.ednevnik.entities.dto;

public class ZakljucnaOcenaDTO {
	private String predmetNaziv;
    private Integer zakljucnaOcena;
	public ZakljucnaOcenaDTO() {
		super();
	}
	public ZakljucnaOcenaDTO(String predmetNaziv, Integer zakljucnaOcena) {
		super();
		this.predmetNaziv = predmetNaziv;
		this.zakljucnaOcena = zakljucnaOcena;
	}
	public String getPredmetNaziv() {
		return predmetNaziv;
	}
	public void setPredmetNaziv(String predmetNaziv) {
		this.predmetNaziv = predmetNaziv;
	}
	public Integer getZakljucnaOcena() {
		return zakljucnaOcena;
	}
	public void setZakljucnaOcena(Integer zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}
	@Override
	public String toString() {
		return "ZakljucnaOcenaDTO [predmetNaziv=" + predmetNaziv + ", zakljucnaOcena=" + zakljucnaOcena + "]";
	}

    
}
