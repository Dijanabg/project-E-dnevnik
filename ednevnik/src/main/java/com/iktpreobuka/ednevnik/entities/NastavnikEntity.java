package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnik")
public class NastavnikEntity extends KorisnikEntity{

	@JsonBackReference
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikPredmetEntity> predajePredmet = new ArrayList<>();
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "odelenje")
	private OdelenjeEntity odelenje;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "predavac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikOdelenjeEntity> nastavnikOdelenje = new ArrayList<>();;
	
	@JsonBackReference
	@OneToMany(mappedBy = "ucenik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikPredmetUcenikEntity> ucenici = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "ocenjivac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<OcenaEntity> ocene = new ArrayList<>();

	public NastavnikEntity() {
		super();
	}

	public NastavnikEntity(List<NastavnikPredmetEntity> predajePredmet, OdelenjeEntity odelenje,
			List<NastavnikOdelenjeEntity> nastavnikOdelenje, List<NastavnikPredmetUcenikEntity> ucenici,
			List<OcenaEntity> ocene) {
		super();
		this.predajePredmet = predajePredmet;
		this.odelenje = odelenje;
		this.nastavnikOdelenje = nastavnikOdelenje;
		this.ucenici = ucenici;
		this.ocene = ocene;
	}

	public List<NastavnikPredmetEntity> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(List<NastavnikPredmetEntity> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public OdelenjeEntity getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(OdelenjeEntity odelenje) {
		this.odelenje = odelenje;
	}

	public List<NastavnikOdelenjeEntity> getNastavnikOdelenje() {
		return nastavnikOdelenje;
	}

	public void setNastavnikOdelenje(List<NastavnikOdelenjeEntity> nastavnikOdelenje) {
		this.nastavnikOdelenje = nastavnikOdelenje;
	}

	public List<NastavnikPredmetUcenikEntity> getUcenici() {
		return ucenici;
	}

	public void setUcenici(List<NastavnikPredmetUcenikEntity> ucenici) {
		this.ucenici = ucenici;
	}

	public List<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	@Override
	public String toString() {
		return "NastavnikEntity [predajePredmet=" + predajePredmet + ", odelenje=" + odelenje + ", nastavnikOdelenje="
				+ nastavnikOdelenje + ", djaci=" + ucenici + ", ocene=" + ocene + "]";
	}

}

