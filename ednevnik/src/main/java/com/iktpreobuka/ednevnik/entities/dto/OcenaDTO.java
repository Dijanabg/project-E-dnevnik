package com.iktpreobuka.ednevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class OcenaDTO {
	
	private Integer id;

	@NotNull(message = "Morate uneti ocenu.")
	@Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.")
	@Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.")
	@JsonView(Views.Private.class)
    private Integer vrednostOcene;
	@JsonView(Views.Private.class)
    private String datum; // Formatiran kao String za jednostavniji rad
    @NotNull(message = "Morate uneti aktivnost!")
    @JsonView(Views.Private.class)
    private EAktivnostEntity aktivnost;
    @NotNull(message = "Morate uneti polugodište!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Polugodište moze biti PRVO ili DRUGO.")
    @JsonView(Views.Private.class)
    private String polugodiste;
    @JsonView(Views.Admin.class)
    //@NotNull(message = "Ocenu može uneti samo odgovarajući nastavnik.")
    private Integer ocenjivacId;
    @NotNull(message = "Ucenik je obavezno polje")
    @JsonView(Views.Admin.class)
    private Integer ucenikId; 
    @JsonView(Views.Private.class)
    private String ucenikImePrezime;
    @JsonView(Views.Private.class)
    private String nastavnikImePrezime;
    @NotNull(message = "Predmet je obavezno polje")
    private Integer predmetId;
    @JsonView(Views.Private.class)
    private String predmetNaziv;
    
    @Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.")
	@Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.")
    
    private Integer zakljucnaOcena;
    @JsonView(Views.Private.class)
    private String zakljucnaOcenaPoruka;

	public OcenaDTO() {
		super();
	}

	public OcenaDTO(Integer id,
			@NotNull(message = "Morate uneti ocenu.") @Min(value = 1, message = "Vrednost ocene mora biti između {value} i 5.") @Max(value = 5, message = "Vrednost ocene mora biti između 1 i {value}.") Integer vrednostOcene,
			String datum,
			@NotNull(message = "Morate uneti aktivnost!") @Pattern(regexp = "^[a-zA-Z]+$", message = "Unesite validnu aktivnost.") @NotNull(message = "Morate uneti aktivnost!") @Pattern(regexp = "^[a-zA-Z]+$", message = "Unesite validnu aktivnost.") EAktivnostEntity aktivnost,
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

	public @NotNull(message = "Morate uneti aktivnost!") EAktivnostEntity getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(@NotNull(message = "Morate uneti aktivnost!")  EAktivnostEntity aktivnost) {
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
	public String getUcenikImePrezime() {
        return ucenikImePrezime;
    }

    public void setUcenikImePrezime(String ucenikImePrezime) {
        this.ucenikImePrezime = ucenikImePrezime;
    }

    public String getNastavnikImePrezime() {
        return nastavnikImePrezime;
    }

    public void setNastavnikImePrezime(String nastavnikImePrezime) {
        this.nastavnikImePrezime = nastavnikImePrezime;
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
	
	public String getZakljucnaOcenaPoruka() {
		return zakljucnaOcenaPoruka;
	}

	public void setZakljucnaOcenaPoruka(String zakljucnaOcenaPoruka) {
		this.zakljucnaOcenaPoruka = zakljucnaOcenaPoruka;
	}

	@Override
	public String toString() {
		return "OcenaDTO [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", polugodiste=" + polugodiste + ", ocenjivacId=" + ocenjivacId + ", ucenikId=" + ucenikId
				+ ", predmetId=" + predmetId + ", predmetNaziv=" + predmetNaziv + ", zakljucnaOcena=" + zakljucnaOcena
				+ "]";
	}
	
}
