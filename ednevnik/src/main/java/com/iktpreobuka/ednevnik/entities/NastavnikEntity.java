package com.iktpreobuka.ednevnik.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nastavnik")
public class NastavnikEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nastavnik_id")
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
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikPredmetEntity> predajePredmet = new ArrayList<>();
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "odelenje")
	private OdelenjeEntity odelenje;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "predavac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<NastavnikOdelenjeEntity> nastavnikOdelenje = new ArrayList<>();;
	
//	@JsonBackReference
//	@OneToMany(mappedBy = "profesor", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	private List<NastavnikPredmetUcenikEntity> ucenici = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "ocenjivac", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<OcenaEntity> ocene = new ArrayList<>();

	public NastavnikEntity() {
		super();
	}

	public NastavnikEntity(Integer id, KorisnikEntity korisnik, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime,
			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			List<NastavnikPredmetEntity> predajePredmet, OdelenjeEntity odelenje,
			List<NastavnikOdelenjeEntity> nastavnikOdelenje, List<OcenaEntity> ocene) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.predajePredmet = predajePredmet;
		this.odelenje = odelenje;
		this.nastavnikOdelenje = nastavnikOdelenje;
		this.ocene = ocene;
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

	public List<NastavnikPredmetEntity> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(List<NastavnikPredmetEntity> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public OdelenjeEntity getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(OdelenjeEntity odelenje) {
		this.odelenje = odelenje;
	}

	public List<NastavnikOdelenjeEntity> getNastavnikOdelenje() {
		return nastavnikOdelenje;
	}

	public void setNastavnikOdelenje(List<NastavnikOdelenjeEntity> nastavnikOdelenje) {
		this.nastavnikOdelenje = nastavnikOdelenje;
	}

	public List<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	@Override
	public String toString() {
		return "NastavnikEntity [id=" + id + ", korisnik=" + korisnik + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", predajePredmet=" + predajePredmet + ", odelenje=" + odelenje
				+ ", nastavnikOdelenje=" + nastavnikOdelenje + ", ocene=" + ocene + "]";
	}

	
//	public List<NastavnikPredmetUcenikEntity> getUcenici() {
//		return ucenici;
//	}
//
//	public void setUcenici(List<NastavnikPredmetUcenikEntity> ucenici) {
//		this.ucenici = ucenici;
//	}

	

}

