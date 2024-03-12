package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "role")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name= "role_id")
	private Integer id;
	
	@Column(name = "role_name", unique = true)
	private String name;
	
	@Version
	protected Integer version;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<KorisnikEntity> korisnici = new ArrayList<>();

	public RoleEntity() {
		super();
	}

	public RoleEntity(Integer id, String name, Integer version, List<KorisnikEntity> korisnici) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.korisnici = korisnici;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<KorisnikEntity> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<KorisnikEntity> korisnici) {
		this.korisnici = korisnici;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", version=" + version + ", korisnici=" + korisnici + "]";
	}

	
}
