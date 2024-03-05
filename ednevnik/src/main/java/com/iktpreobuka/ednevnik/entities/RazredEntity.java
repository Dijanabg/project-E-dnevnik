package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "razred")
public class RazredEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "razred_id")
	private Integer id;
	
	@Column(name = "razred")
	@Min(value = 1, message = "Broj razreda moze biti najmanje {value}")
	@Max(value = 8, message = "Broj razreda moze biti najvise {value}")
	@NotNull(message = "Razred mora biti unet.")
	private Integer razred;
	
	@JsonBackReference
	@OneToMany(mappedBy = "odelenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<OdelenjeEntity> odelenja = new ArrayList<>();
}
