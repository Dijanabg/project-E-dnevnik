package com.iktpreobuka.ednevnik.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "skolska_godina")
public class SkolskaGodinaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skolgod_id")
	private Integer id;
	
	@Column(name = "oznaka")
	@JsonView(Views.Public.class)
	private String oznaka;
	
	@Version
	protected Integer version;
	
	@OneToMany(mappedBy = "skolskaGodina")
    @JsonIgnore // da se izbegne potencijalna beskonačna rekurzija prilikom serijalizacije u JSON
    @JsonView(Views.Private.class)
    private List<RazredEntity> razredi;

	public SkolskaGodinaEntity() {
		super();
	}

	public SkolskaGodinaEntity(Integer id, String oznaka, Integer version, List<RazredEntity> razredi) {
		super();
		this.id = id;
		this.oznaka = oznaka;
		this.version = version;
		this.razredi = razredi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<RazredEntity> getRazredi() {
		return razredi;
	}

	public void setRazredi(List<RazredEntity> razredi) {
		this.razredi = razredi;
	}

	@Override
	public String toString() {
		return "SkolskaGodinaEntity [id=" + id + ", oznaka=" + oznaka + ", version=" + version + ", razredi=" + razredi
				+ "]";
	}

}
