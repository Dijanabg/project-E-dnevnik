package com.iktpreobuka.ednevnik.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ucenik")
public class UcenikEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ucenik_id")
    private Integer id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id", unique = true)
	private KorisnikEntity korisnikUcenik;
    
    @Column(name = "ime")
    @JsonView(Views.Private.class)
	private String ime;

	@Column(name = "prezime")
	@JsonView(Views.Private.class)
	private String prezime;
    
	@Column(name = "email", unique = true)
	@JsonView(Views.Private.class)
	private String email;
	
	//roditelj ucenika
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj_id", unique = true)
	@JsonView(Views.Private.class)
	private RoditeljEntity roditelj;
	
	@Version
	protected Integer version;
	
	//odelenje ucenika
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonView(Views.Private.class)
	@JoinColumn(name = "odelenje_id")
	private OdelenjeEntity odelenje;

	//ocene ucenika
	@OneToMany(mappedBy = "ucenik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonView(Views.Private.class)
	private List<OcenaEntity> ocene;
	
	public UcenikEntity() {
		super();
	}

	public UcenikEntity(Integer id, KorisnikEntity korisnikUcenik, String ime, String prezime, String email,
			RoditeljEntity roditelj, Integer version, OdelenjeEntity odelenje, List<OcenaEntity> ocene) {
		super();
		this.id = id;
		this.korisnikUcenik = korisnikUcenik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.roditelj = roditelj;
		this.version = version;
		this.odelenje = odelenje;
		this.ocene = ocene;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnikUcenik() {
		return korisnikUcenik;
	}

	public void setKorisnikUcenik(KorisnikEntity korisnikUcenik) {
		this.korisnikUcenik = korisnikUcenik;
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

	public RoditeljEntity getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OdelenjeEntity getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(OdelenjeEntity odelenje) {
		this.odelenje = odelenje;
	}

	public List<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	@Override
	public String toString() {
		return "UcenikEntity [id=" + id + ", korisnikUcenik=" + korisnikUcenik + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", roditelj=" + roditelj + ", version=" + version + ", odelenje=" + odelenje
				+ ", ocene=" + ocene + "]";
	}

	
}