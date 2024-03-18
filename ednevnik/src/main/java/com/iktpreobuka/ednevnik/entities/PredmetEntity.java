package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "predmet", uniqueConstraints = {@UniqueConstraint(columnNames = {"naziv_predmeta", "razred_id"})})
public class PredmetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "predmet_id")
	private Integer id;
	
	@Column(name = "naziv_predmeta")
	@JsonView(Views.Public.class)
	private String nazivPredmeta;
	
	@Column(name = "fond")
	@JsonView(Views.Public.class)
	private Integer casovaNedeljno;
	
	@Version
	private Integer version;
	
	//predmeti po razredu
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Public.class)
	@JoinColumn(name = "razred_id", nullable = false)
	private RazredEntity razred;
	
	//nastavnici koji predaju predmet
	@JsonBackReference
	@OneToMany (mappedBy = "predmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JsonView(Views.Public.class)
	@JsonIgnore
	protected List<NastavnikPredmetEntity> nastavnici = new ArrayList<>();

	@JsonBackReference
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Public.class)
	private List<NastavnikOdelenjeEntity> nastavnikOdelenje = new ArrayList<>();
	//private Set<NastavnikOdelenjeEntity> nastavnikOdelenje = new HashSet<>();
	//set kolekcija koja ne dozvoljava duplikate
	
	
	public List<NastavnikPredmetEntity> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<NastavnikPredmetEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}

	public PredmetEntity() {
		super();
	}

	

	public PredmetEntity(Integer id, String nazivPredmeta, Integer casovaNedeljno,
			Integer version, RazredEntity razred, List<NastavnikPredmetEntity> nastavnici) {
		super();
		this.id = id;
		this.nazivPredmeta = nazivPredmeta;
		this.casovaNedeljno = casovaNedeljno;
		this.version = version;
		this.razred = razred;
		this.nastavnici = nastavnici;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public Integer getCasovaNedeljno() {
		return casovaNedeljno;
	}

	public void setCasovaNedeljno(Integer casovaNedeljno) {
		this.casovaNedeljno = casovaNedeljno;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public RazredEntity getRazred() {
		return razred;
	}

	public void setRazred(RazredEntity razred) {
		this.razred = razred;
	}

	@Override
	public String toString() {
		return "PredmetEntity [id=" + id + ", nazivPredmeta=" + nazivPredmeta + ", casovaNedeljno=" + casovaNedeljno
				+ ", version=" + version + ", razred=" + razred + ", nastavnici="
				+ nastavnici + "]";
	}

	
}
