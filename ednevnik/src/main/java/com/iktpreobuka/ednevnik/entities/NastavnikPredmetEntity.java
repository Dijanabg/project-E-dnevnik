package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnikPredmet")
public class NastavnikPredmetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Version
	private Integer version;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "nastavnik")
	private NastavnikEntity nastavnik;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet")
	private PredmetEntity predmet;

	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "skolgod_id")
	@NotNull(message = "Skolska godina mora biti uneta.")
	private SkolskaGodinaEntity skolskaGodina;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Polugodiste mora biti uneto.")
	private EPolugodisteEntity polugodiste;
	
	//nastavnici koji predaju predmet uceniku
	@JsonIgnore
	@OneToMany(mappedBy = "nastavnikPredmet", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<NastavnikPredmetUcenikEntity> nastavnikPredmetUcenik = new ArrayList<>();
	
	public NastavnikPredmetEntity() {
		super();
	}

	public NastavnikPredmetEntity(Integer id, NastavnikEntity nastavnik, PredmetEntity predmet,
			@NotNull(message = "Skolska godina mora biti uneta.") SkolskaGodinaEntity skolskaGodina,
			@NotNull(message = "Polugodiste mora biti uneto.") EPolugodisteEntity polugodiste) {
		super();
		this.id = id;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
		this.skolskaGodina = skolskaGodina;
		this.polugodiste = polugodiste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	@Override
	public String toString() {
		return "NastavnikPredmetEntity [id=" + id + ", nastavnik=" + nastavnik + ", predmet=" + predmet
				+ ", skolskaGodina=" + skolskaGodina + ", polugodiste=" + polugodiste + "]";
	}
	
}
