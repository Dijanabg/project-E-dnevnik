package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OcenaDTO {
	private Integer id;

	@NotNull(message = "Morate uneti ocenu.")
	@Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.")
	@Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.")
    private Integer vrednostOcene;
	
    private String datum; // Formatiran kao String za jednostavniji rad
    private String aktivnost;
    @NotNull(message = "Morate uneti polugodište!")
    private String polugodiste;
    @NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.")
    private Integer ocenjivacId;
    
    
	public OcenaDTO() {
		super();

	}


	public OcenaDTO(Integer id,
			@NotNull(message = "Morate uneti ocenu.") @Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.") @Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.") Integer vrednostOcene,
			String datum, String aktivnost, @NotNull(message = "Morate uneti polugodište!") String polugodiste,
			@NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.") Integer ocenjivacId) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.polugodiste = polugodiste;
		this.ocenjivacId = ocenjivacId;
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


	@Override
	public String toString() {
		return "OcenaDTO [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", polugodiste=" + polugodiste + ", ocenjivacId=" + ocenjivacId + "]";
	}
	
}
