package com.iktpreobuka.ednevnik.entities.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class OcenaDTO {
	private Integer id;

	@NotNull(message = "Morate uneti ocenu.")
	@Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.")
	@Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.")
    private Integer vrednostOcene;
	
    private String datum; // Formatiran kao String za jednostavniji rad
    @NotNull(message = "Morate uneti aktivnost!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Unesite validnu aktivnost.")
    private String aktivnost;
    @NotNull(message = "Morate uneti polugodište!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Polugodište moze biti PRVO ili DRUGO.")
    private String polugodiste;
    @NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.")
    private Integer ocenjivacId;
    @NotNull(message = "Ucenik je obavezno polje")
    private Integer ucenikId; 
    @NotNull(message = "Predmet je obavezno polje")
    private Integer predmetId;
    
    private String predmetNaziv;
    
    @Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.")
	@Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.")
    private Integer zakljucnaOcena;
    
	public OcenaDTO() {
		super();
	}

	public OcenaDTO(Integer id,
			@NotNull(message = "Morate uneti ocenu.") @Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.") @Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.") Integer vrednostOcene,
			String datum,
			@NotNull(message = "Morate uneti aktivnost!") @Pattern(regexp = "^[a-zA-Z]+$", message = "Unesite validnu aktivnost.") String aktivnost,
			@NotNull(message = "Morate uneti polugodište!") @Pattern(regexp = "^[a-zA-Z]+$", message = "Polugodište moze biti PRVO ili DRUGO.") String polugodiste,
			@NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.") Integer ocenjivacId,
			@NotNull(message = "Ucenik je obavezno polje") Integer ucenikId,
			@NotNull(message = "Predmet je obavezno polje") Integer predmetId, String predmetNaziv,
			@Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.") @Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.") Integer zakljucnaOcena) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.polugodiste = polugodiste;
		this.ocenjivacId = ocenjivacId;
		this.ucenikId = ucenikId;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.zakljucnaOcena = zakljucnaOcena;
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

	public Integer getUcenikId() {
		return ucenikId;
	}

	public void setUcenikId(Integer ucenikId) {
		this.ucenikId = ucenikId;
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

	public Integer getZakljucnaOcena() {
		return zakljucnaOcena;
	}

	public void setZakljucnaOcena(Integer zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}

	@Override
	public String toString() {
		return "OcenaDTO [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", polugodiste=" + polugodiste + ", ocenjivacId=" + ocenjivacId + ", ucenikId=" + ucenikId
				+ ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv + ", zakljucnaOcena=" + zakljucnaOcena
				+ "]";
	}
	
}
