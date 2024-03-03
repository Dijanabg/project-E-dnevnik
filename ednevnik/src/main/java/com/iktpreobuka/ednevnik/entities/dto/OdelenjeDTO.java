package com.iktpreobuka.ednevnik.entities.dto;

public class OdelenjeDTO {
	private Integer id;
    private boolean aktivno;
    private Integer razred;
    private Integer odelenje;
    private Integer skolskaGodinaId; // Samo ID školske godine, da se izbegne ciklična zavisnost
    private Integer verzija;
    
	public OdelenjeDTO() {
		super();
	}

	public OdelenjeDTO(Integer id, boolean aktivno, Integer razred, Integer odelenje, Integer skolskaGodinaId,
			Integer verzija) {
		super();
		this.id = id;
		this.aktivno = aktivno;
		this.razred = razred;
		this.odelenje = odelenje;
		this.skolskaGodinaId = skolskaGodinaId;
		this.verzija = verzija;
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

	public Integer getSkolskaGodinaId() {
		return skolskaGodinaId;
	}

	public void setSkolskaGodinaId(Integer skolskaGodinaId) {
		this.skolskaGodinaId = skolskaGodinaId;
	}

	public Integer getVerzija() {
		return verzija;
	}

	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}

	@Override
	public String toString() {
		return "OdelenjeDTO [id=" + id + ", aktivno=" + aktivno + ", razred=" + razred + ", odelenje=" + odelenje
				+ ", skolskaGodinaId=" + skolskaGodinaId + ", verzija=" + verzija + "]";
	}
    
    
}
