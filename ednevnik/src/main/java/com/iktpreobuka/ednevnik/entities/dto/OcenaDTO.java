package com.iktpreobuka.ednevnik.entities.dto;

public class OcenaDTO {
	private Integer id;
    private Integer vrednostOcene;
    private String datum; // Formatiran kao String za jednostavniji rad
    private String aktivnost;
    private String semestar;
    private Integer ocenjivacId;
    private String ocenjivacIme; // ??
    private Integer ucenikId;
    private String ucenikIme; // ??
    private Integer nastavnikPredmetUcenikId; //VEZA
    
	public OcenaDTO() {
		super();

	}
	public OcenaDTO(Integer id, Integer vrednostOcene, String datum, String aktivnost, String semestar,
			Integer ocenjivacId, String ocenjivacIme, Integer ucenikId, String ucenikIme,
			Integer nastavnikPredmetUcenikId) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.semestar = semestar;
		this.ocenjivacId = ocenjivacId;
		this.ocenjivacIme = ocenjivacIme;
		this.ucenikId = ucenikId;
		this.ucenikIme = ucenikIme;
		this.nastavnikPredmetUcenikId = nastavnikPredmetUcenikId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVrednostOcene() {
		return vrednostOcene;
	}
	public void setVrednostOcene(Integer vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getAktivnost() {
		return aktivnost;
	}
	public void setAktivnost(String aktivnost) {
		this.aktivnost = aktivnost;
	}
	public String getSemestar() {
		return semestar;
	}
	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}
	public Integer getOcenjivacId() {
		return ocenjivacId;
	}
	public void setOcenjivacId(Integer ocenjivacId) {
		this.ocenjivacId = ocenjivacId;
	}
	public String getOcenjivacIme() {
		return ocenjivacIme;
	}
	public void setOcenjivacIme(String ocenjivacIme) {
		this.ocenjivacIme = ocenjivacIme;
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
	public Integer getNastavnikPredmetUcenikId() {
		return nastavnikPredmetUcenikId;
	}
	public void setNastavnikPredmetUcenikId(Integer nastavnikPredmetUcenikId) {
		this.nastavnikPredmetUcenikId = nastavnikPredmetUcenikId;
	}
	@Override
	public String toString() {
		return "OcenaDTO [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", semestar=" + semestar + ", ocenjivacId=" + ocenjivacId + ", ocenjivacIme="
				+ ocenjivacIme + ", ucenikId=" + ucenikId + ", ucenikIme=" + ucenikIme + ", nastavnikPredmetUcenikId="
				+ nastavnikPredmetUcenikId + "]";
	}
    
    
}
