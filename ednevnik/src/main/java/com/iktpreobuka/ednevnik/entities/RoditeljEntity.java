package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roditelj")
public class RoditeljEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roditelj_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private KorisnikEntity korisnik;
    
    @Column(name = "ime")
	@NotNull(message = "Ime mora biti uneto.")
	private String ime;

	@Column(name = "prezime")
	@NotNull(message = "Prezime mora biti uneto.")
	private String prezime;
	
	@Column(name = "email", unique = true)
	@NotNull(message = "Email must be provided.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	private String email;
	
	@JsonBackReference
	//@JsonIgnore
	@OneToMany(mappedBy = "roditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	protected List<UcenikEntity> dete = new ArrayList<>();

	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoditeljEntity(Integer id, KorisnikEntity korisnik, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime,
			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			List<UcenikEntity> dete) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.dete = dete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikEntity korisnik) {
		this.korisnik = korisnik;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UcenikEntity> getDete() {
		return dete;
	}

	public void setDete(List<UcenikEntity> dete) {
		this.dete = dete;
	}

	@Override
	public String toString() {
		return "RoditeljEntity [id=" + id + ", korisnik=" + korisnik + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", dete=" + dete + "]";
	}
	
	

}
