package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "odelenje", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"razred_id", "odelenje"})
	})
public class OdelenjeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "odelenje_id")
	private Integer id;
	
	@Column(name = "odelenje")
	@JsonView(Views.Public.class)
	private Integer odelenje;

	@Version
	protected Integer version;
	
	//razred
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn	(name = "razred_id")
	@JsonView(Views.Public.class)
	private RazredEntity razred;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "razredniStaresina")
	@JsonView(Views.Public.class)
	private NastavnikEntity razredniStaresina;
	
	//ucenici u odelenju
	@JsonBackReference
	@OneToMany(mappedBy = "odelenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JsonView(Views.Private.class)
	protected List<UcenikEntity> ucenici = new ArrayList<>();

	//nastavnici koji predaju odelenju
	@JsonBackReference
	@OneToMany(mappedBy = "odelenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JsonView(Views.Private.class)
	private List <NastavnikOdelenjeEntity> nastavnici = new ArrayList<>();

	
	public OdelenjeEntity() {
		super();
	}


	public OdelenjeEntity(Integer id, Integer odelenje, Integer version, RazredEntity razred,
			NastavnikEntity razredniStaresina, List<UcenikEntity> ucenici, List<NastavnikOdelenjeEntity> nastavnici) {
		super();
		this.id = id;
		this.odelenje = odelenje;
		this.version = version;
		this.razred = razred;
		this.razredniStaresina = razredniStaresina;
		this.ucenici = ucenici;
		this.nastavnici = nastavnici;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOdelenje() {
		return odelenje;
	}


	public void setOdelenje(Integer odelenje) {
		this.odelenje = odelenje;
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


	public NastavnikEntity getRazredniStaresina() {
		return razredniStaresina;
	}


	public void setRazredniStaresina(NastavnikEntity razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
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


	@Override
	public String toString() {
		return "OdelenjeEntity [id=" + id + ", odelenje=" + odelenje + ", version=" + version + ", razred=" + razred
				+ ", razredniStaresina=" + razredniStaresina + ", ucenici=" + ucenici + ", nastavnici=" + nastavnici
				+ "]";
	}


}