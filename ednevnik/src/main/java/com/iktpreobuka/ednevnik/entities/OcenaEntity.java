package com.iktpreobuka.ednevnik.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ocena")
public class OcenaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ocena_id")
	private Integer id;
	
	@Column(name = "vrednost_ocene")
	@JsonView(Views.Private.class)
	private Integer vrednostOcene;
	
	@Column(name = "datum")
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	@JsonView(Views.Private.class)
	private Date datum;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	private EAktivnostEntity aktivnost;

	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	private EPolugodisteEntity polugodiste;
	
	@Column(name = "zakljucna_ocena")
	@JsonView(Views.Private.class)
	private Integer zakljucnaOcena;
	
	@Version
	private Integer version;
	
	//nastavnik koji daje ocenu
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ocenjivac")
	private NastavnikEntity ocenjivac;
	
	//ucenik koji dobija ocenu
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn	(name = "ucenik")
	private UcenikEntity ucenik;

	@ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private PredmetEntity predmet;
	
	public OcenaEntity() {
		super();
	}

	public OcenaEntity(Integer id, Integer vrednostOcene, Date datum, EAktivnostEntity aktivnost,
			EPolugodisteEntity polugodiste, Integer zakljucnaOcena, Integer version, NastavnikEntity ocenjivac,
			UcenikEntity ucenik, PredmetEntity predmet) {
		super();
		this.id = id;
		this.vrednostOcene = vrednostOcene;
		this.datum = datum;
		this.aktivnost = aktivnost;
		this.polugodiste = polugodiste;
		this.zakljucnaOcena = zakljucnaOcena;
		this.version = version;
		this.ocenjivac = ocenjivac;
		this.ucenik = ucenik;
		this.predmet = predmet;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public EAktivnostEntity getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(EAktivnostEntity aktivnost) {
		this.aktivnost = aktivnost;
	}

	public EPolugodisteEntity getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(EPolugodisteEntity polugodiste) {
		this.polugodiste = polugodiste;
	}

	public Integer getZakljucnaOcena() {
		return zakljucnaOcena;
	}

	public void setZakljucnaOcena(Integer zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public NastavnikEntity getOcenjivac() {
		return ocenjivac;
	}

	public void setOcenjivac(NastavnikEntity ocenjivac) {
		this.ocenjivac = ocenjivac;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	@Override
	public String toString() {
		return "OcenaEntity [id=" + id + ", vrednostOcene=" + vrednostOcene + ", datum=" + datum + ", aktivnost="
				+ aktivnost + ", polugodiste=" + polugodiste + ", zakljucnaOcena=" + zakljucnaOcena + ", version="
				+ version + ", ocenjivac=" + ocenjivac + ", ucenik=" + ucenik + ", predmet=" + predmet + "]";
	}

	
}
