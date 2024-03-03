package com.iktpreobuka.ednevnik.entities;

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
import jakarta.persistence.Table;

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
	private NastavnikEntity predavac;
	
	
	@JsonManagedReference
	@ManyToOne (fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn (name = "razred")
	private OdelenjeEntity razred;
}
