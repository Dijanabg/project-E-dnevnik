package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "odelenje")
public class OdelenjeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "odelenje_id")
	private Integer id;
	
	@Column(name = "aktivno")
	private boolean aktivno = Boolean.FALSE;
	
	@Column(name = "odelenje")
	private Integer odelenje;
	
	@ManyToOne
	@JoinColumn(name = "skolska_godina_id") // Ovo predstavlja ime kolone u tabeli 'odelenje' koja referencira 'id' kolonu u tabeli 'skolska_godina'
	private SkolskaGodinaEntity skolskaGodina;

	@Version
	protected Integer verzija;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "razredni_staresina_id")
    private NastavnikEntity razredniStaresina;
	
	//razred
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn	(name = "razred")
	private RazredEntity razred;
	
	//ucenici u odelenju
	@JsonBackReference
	@OneToMany(mappedBy = "odelenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<UcenikEntity> ucenici = new ArrayList<>();

	//nastavnici koji predaju odelenju
	@JsonBackReference
	@OneToMany(mappedBy = "odelenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List <NastavnikOdelenjeEntity> nastavnici = new ArrayList<>();

	
	public OdelenjeEntity() {
		super();
	}


	public OdelenjeEntity(Integer id, boolean aktivno,
			@Min(value = 1, message = "Broj odelenja moze biti najmanje {value}") @Max(value = 10, message = "Broj odelenja moze biti najvise {value}") @NotNull(message = "Broj odelenja mora biti unet.") Integer odelenje,
			@NotNull(message = "Školska godina mora biti unesena.") SkolskaGodinaEntity skolskaGodina, Integer verzija,
			NastavnikEntity razredniStaresina, RazredEntity razred, List<UcenikEntity> ucenici,
			List<NastavnikOdelenjeEntity> nastavnici) {
		super();
		this.id = id;
		this.aktivno = aktivno;
		this.odelenje = odelenje;
		this.skolskaGodina = skolskaGodina;
		this.verzija = verzija;
		this.razredniStaresina = razredniStaresina;
		this.razred = razred;
		this.ucenici = ucenici;
		this.nastavnici = nastavnici;
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


	public Integer getOdelenje() {
		return odelenje;
	}


	public void setOdelenje(Integer odelenje) {
		this.odelenje = odelenje;
	}


	public SkolskaGodinaEntity getSkolskaGodina() {
		return skolskaGodina;
	}


	public void setSkolskaGodina(SkolskaGodinaEntity skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}


	public Integer getVerzija() {
		return verzija;
	}


	public void setVerzija(Integer verzija) {
		this.verzija = verzija;
	}


	public NastavnikEntity getRazredniStaresina() {
		return razredniStaresina;
	}


	public void setRazredniStaresina(NastavnikEntity razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
	}


	public RazredEntity getRazred() {
		return razred;
	}


	public void setRazred(RazredEntity razred) {
		this.razred = razred;
	}


	public List<UcenikEntity> getUcenici() {
		return ucenici;
	}


	public void setUcenici(List<UcenikEntity> ucenici) {
		this.ucenici = ucenici;
	}


	public List<NastavnikOdelenjeEntity> getNastavnici() {
		return nastavnici;
	}


	public void setNastavnici(List<NastavnikOdelenjeEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}

}