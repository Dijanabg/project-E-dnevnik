package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//dodaje podklase kao tabele
@Table(name = "role")
public class RoleEntity {
	
	@Id
	@GeneratedValue
	@Column( name= "role_id")
	private Integer id;
	
	@Column(name = "role_name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<KorisnikEntity> korisnici = new ArrayList<>();

	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleEntity(Integer id, String name, List<KorisnikEntity> korisnici) {
		super();
		this.id = id;
		this.name = name;
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

	public List<KorisnikEntity> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<KorisnikEntity> korisnici) {
		this.korisnici = korisnici;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", korisnici=" + korisnici + "]";
	}
	
	
}
