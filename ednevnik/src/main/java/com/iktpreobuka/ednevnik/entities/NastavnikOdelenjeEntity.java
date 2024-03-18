package com.iktpreobuka.ednevnik.entities;

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
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnikOdelenje")
public class NastavnikOdelenjeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@JsonManagedReference
	@ManyToOne (fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn (name = "nastavnik")
	@JsonView(Views.Private.class)
	private NastavnikEntity predavac;
	
	
	@JsonManagedReference
	@ManyToOne (fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn (name = "odelenje")
	@JsonView(Views.Private.class)
	private OdelenjeEntity odelenje;
	
	@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "predmet")
	@JsonView(Views.Private.class)
    private PredmetEntity predmet;

	@Version
	protected Integer version;

	public NastavnikOdelenjeEntity() {
		super();
	}

	public NastavnikOdelenjeEntity(Integer id, NastavnikEntity predavac, OdelenjeEntity odelenje, PredmetEntity predmet,
			Integer version) {
		super();
		this.id = id;
		this.predavac = predavac;
		this.odelenje = odelenje;
		this.predmet = predmet;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NastavnikEntity getPredavac() {
		return predavac;
	}

	public void setPredavac(NastavnikEntity predavac) {
		this.predavac = predavac;
	}

	public OdelenjeEntity getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(OdelenjeEntity odelenje) {
		this.odelenje = odelenje;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "NastavnikOdelenjeEntity [id=" + id + ", predavac=" + predavac + ", odelenje=" + odelenje + ", predmet="
				+ predmet + ", version=" + version + "]";
	}

}
