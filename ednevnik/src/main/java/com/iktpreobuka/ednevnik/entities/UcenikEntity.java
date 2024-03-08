package com.iktpreobuka.ednevnik.entities;

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
@Table(name = "ucenik")
public class UcenikEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ucenik_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private KorisnikEntity korisnik;
    
    @Column(name = "ime")
	private String ime;

	@Column(name = "prezime")
	private String prezime;
    
	@Column(name = "email", unique = true)
	private String email;
	
	//roditelj ucenika
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj")
	private RoditeljEntity roditelj;
	
	//odelenje ucenika
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn	(name = "odelenje")
	private OdelenjeEntity odelenje;
	
	//nastavnici uceniku
	@JsonBackReference
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<NastavnikPredmetUcenikEntity> nastavnici;

	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UcenikEntity(Integer id, KorisnikEntity korisnik, @NotNull(message = "Ime mora biti uneto.") String ime,
			@NotNull(message = "Prezime mora biti uneto.") String prezime,
			@NotNull(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			RoditeljEntity roditelj, OdelenjeEntity odelenje, List<NastavnikPredmetUcenikEntity> nastavnici) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.roditelj = roditelj;
		this.odelenje = odelenje;
		this.nastavnici = nastavnici;
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

	public RoditeljEntity getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}

	public OdelenjeEntity getOdelenje() {
		return odelenje;
	}

	public void setOdelenje(OdelenjeEntity odelenje) {
		this.odelenje = odelenje;
	}

	public List<NastavnikPredmetUcenikEntity> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<NastavnikPredmetUcenikEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}

	@Override
	public String toString() {
		return "UcenikEntity [id=" + id + ", korisnik=" + korisnik + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", roditelj=" + roditelj + ", odelenje=" + odelenje + ", nastavnici="
				+ nastavnici + "]";
	}
	
	

}