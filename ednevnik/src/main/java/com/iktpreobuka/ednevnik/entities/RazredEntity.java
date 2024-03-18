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
//@Table(name = "razred")
@Table(name = "razred", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"skolska_godina_id", "razred"})
	})
public class RazredEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "razred_id")
	private Integer id;
	
	@Column(name = "razred")
	@JsonView(Views.Public.class)
	private Integer razred;
	@ManyToOne
	@JoinColumn(name = "skolska_godina_id") 
	@JsonView(Views.Public.class)
	private SkolskaGodinaEntity skolskaGodina;
	
	@Version
	protected Integer version;
	
	@JsonBackReference
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JsonView(Views.Public.class)
	protected List<OdelenjeEntity> odelenja = new ArrayList<>();

	@OneToMany(mappedBy = "razred", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonView(Views.Public.class)
	private List<PredmetEntity> predmetiPoRazredu;
	
	public RazredEntity() {
		super();
	}

	public RazredEntity(Integer id, Integer razred, SkolskaGodinaEntity skolskaGodina, Integer version,
			List<OdelenjeEntity> odelenja, List<PredmetEntity> predmetiPoRazredu) {
		super();
		this.id = id;
		this.razred = razred;
		this.skolskaGodina = skolskaGodina;
		this.version = version;
		this.odelenja = odelenja;
		this.predmetiPoRazredu = predmetiPoRazredu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public SkolskaGodinaEntity getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(SkolskaGodinaEntity skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<OdelenjeEntity> getOdelenja() {
		return odelenja;
	}

	public void setOdelenja(List<OdelenjeEntity> odelenja) {
		this.odelenja = odelenja;
	}

	public List<PredmetEntity> getPredmetiPoRazredu() {
		return predmetiPoRazredu;
	}

	public void setPredmetiPoRazredu(List<PredmetEntity> predmetiPoRazredu) {
		this.predmetiPoRazredu = predmetiPoRazredu;
	}

	@Override
	public String toString() {
		return "RazredEntity [id=" + id + ", razred=" + razred + ", skolskaGodina=" + skolskaGodina + ", version="
				+ version + ", odelenja=" + odelenja + ", predmetiPoRazredu=" + predmetiPoRazredu + "]";
	}

	
}
