package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OcenaDTO {
	private Integer id;

	@NotNull(message = "Morate uneti ocenu.")
	@Size(min=1, max=5, message = "Vrednost ocene mora biti izmedju {min} i {max} karaktera duga.")
    private Integer vrednostOcene;
	
    private String datum; // Formatiran kao String za jednostavniji rad
    private String aktivnost;
    @NotNull(message = "Morate uneti polugodište!")
    private String polugodiste;
    @NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.")
    private Integer ocenjivacId;
    private String ocenjivacIme; // ??
    private Integer ucenikId;
    private String ucenikIme; // ??
    private Integer nastavnikPredmetUcenikId; //VEZA
    
	public OcenaDTO() {
		super();

	}
	public OcenaDTO(Integer id, Integer vrednostOcene, String datum, String aktivnost, String polugodiste,
			Integer ocenjivacId, String ocenjivacIme, Integer ucenikId, String ucenikIme,
			Integer nastavnikPredmetUcenikId) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.polugodiste = polugodiste;
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
	public String getPolugodiste() {
		return polugodiste;
	}
	public void setPolugodiste(String polugodiste) {
		this.polugodiste = polugodiste;
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
				+ aktivnost + ", semestar=" + polugodiste + ", ocenjivacId=" + ocenjivacId + ", ocenjivacIme="
				+ ocenjivacIme + ", ucenikId=" + ucenikId + ", ucenikIme=" + ucenikIme + ", nastavnikPredmetUcenikId="
				+ nastavnikPredmetUcenikId + "]";
	}
    
    
}
