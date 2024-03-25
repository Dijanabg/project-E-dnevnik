package com.iktpreobuka.ednevnik.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.OcenaEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.entities.dto.ZakljucnaOcenaDTO;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.OcenaMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikOdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.OcenaRepository;
import com.iktpreobuka.ednevnik.repositories.OdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;


@Service
public class OcenaServiceImpl implements OcenaService{
	private static final Logger log = LoggerFactory.getLogger(OcenaServiceImpl.class);
	@Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private NastavnikOdelenjeRepository nastavnikOdelenjeRepository;

    @Autowired
    private UcenikRepository ucenikRepository;

    @Autowired
    private NastavnikRepository nastavnikRepository;
    
    @Autowired
    private OcenaMapper ocenaMapper;

    @Autowired
    private OdelenjeRepository odelenjeRepository;

    @Autowired
    private PredmetRepository predmetRepository;
    
    @Autowired
    private EmailService emailService;

    @Override
	@Transactional
	public OcenaDTO dodajOcenu(OcenaDTO ocenaDTO) {
    	
	    // Dobijanje korisničkog imena ulogovanog korisnika
	    String ulogovaniKorisnikUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    // Pronalaženje ulogovanog nastavnika na osnovu korisničkog imena
	    NastavnikEntity ulogovaniNastavnik = nastavnikRepository.findByKorisnikNastavnikKorisnickoIme(ulogovaniKorisnikUsername)
	            .orElseThrow(() -> new ResourceNotFoundException("Nije pronađen nastavnik sa korisničkim imenom: " + ulogovaniKorisnikUsername));

	    
	    UcenikEntity ucenik = ucenikRepository.findById(ocenaDTO.getUcenikId())
	            .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
	    
	    PredmetEntity predmet = predmetRepository.findById(ocenaDTO.getPredmetId())
	            .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));
	    OdelenjeEntity odelenje = odelenjeRepository.findById(ucenik.getOdelenje().getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Odeljenje nije pronađeno."));

	    // Provera da li ulogovani nastavnik predaje dati predmet u odelenju učenika
	    boolean predajePredmetUOdeljenju = nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(ulogovaniNastavnik, odelenje, predmet);
	    if (!predajePredmetUOdeljenju) {
	        throw new AccessDeniedException("Nastavnik ne predaje dati predmet u odeljenju učenika.");
	    }
	 // Provera da li postoji zaključna ocena među ocenama za datog učenika i predmet
	    boolean postojiZakljucnaOcena = ocenaRepository.findByUcenikAndPredmet(ucenik, predmet).stream()
	            .anyMatch(ocena -> ocena.getZakljucnaOcena() != null);

	    if (postojiZakljucnaOcena) {
	        throw new IllegalStateException("Zaključna ocena za predmet je već postavljena. Ne možete dodati nove ocene.");
	    }
	    log.info("Čuva se nova ocena: {}", ocenaDTO);
	    // Kreiranje nove ocene
	    OcenaEntity novaOcena = ocenaMapper.toEntity(ocenaDTO, ulogovaniNastavnik);
	    novaOcena.setVrednostOcene(ocenaDTO.getVrednostOcene());
	    EAktivnostEntity aktivnostEnum = ocenaDTO.getAktivnost();
	    novaOcena.setAktivnost(aktivnostEnum);
	    
	    EPolugodisteEntity polugodisteEnum = EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste().toUpperCase());
	    novaOcena.setPolugodiste(polugodisteEnum);
	    
	    novaOcena.setUcenik(ucenik);
	    novaOcena.setPredmet(predmet);
	    // Ovde postavljamo ulogovanog nastavnika kao ocenjivača
	    novaOcena.setOcenjivac(ulogovaniNastavnik);
	    novaOcena.setDatum(new Date());
	    novaOcena = ocenaRepository.save(novaOcena);
	    try {
	        emailService.posaljiMejlRoditelju(novaOcena);
	    } catch (Exception e) {
	        log.error("Nepredviđena greška: " + e.getMessage());
    }
	    return ocenaMapper.toDto(novaOcena);
	}	
    
    @Override
    @Transactional
    public OcenaDTO updateOcenu(Integer ocenaId, OcenaDTO ocenaDTO) {
        // Pronađi postojeću ocenu po ID-u
        OcenaEntity postojecaOcena = ocenaRepository.findById(ocenaId)
                .orElseThrow(() -> new ResourceNotFoundException("Ocena nije pronađena."));

        // Provera da li ucenik, nastavnik, i predmet postoje
        UcenikEntity ucenik = ucenikRepository.findById(ocenaDTO.getUcenikId())
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        NastavnikEntity nastavnik = nastavnikRepository.findById(ocenaDTO.getOcenjivacId())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen."));
        PredmetEntity predmet = predmetRepository.findById(ocenaDTO.getPredmetId())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));

        // Provera da li nastavnik predaje dati predmet u odeljenju ucenika
        if (!nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(nastavnik, ucenik.getOdelenje(), predmet)) {
            throw new ResourceNotFoundException("Nastavnik ne predaje dati predmet u odeljenju učenika.");
        }

        // Ažuriranje postojeće ocene sa novim vrednostima
        postojecaOcena.setVrednostOcene(ocenaDTO.getVrednostOcene());
        postojecaOcena.setAktivnost(ocenaDTO.getAktivnost());
        postojecaOcena.setPolugodiste(EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste().toUpperCase()));
        postojecaOcena.setUcenik(ucenik);
        postojecaOcena.setPredmet(predmet);
        postojecaOcena.setOcenjivac(nastavnik);
        postojecaOcena.setDatum(new Date()); // Ažurirajte datum ako je to potrebno

        OcenaEntity azuriranaOcena = ocenaRepository.save(postojecaOcena);

        return ocenaMapper.toDto(azuriranaOcena);
    }

    @Override
    @Transactional
    public void obrisiOcenu(Integer ocenaId) {
    	 log.info("Briše se ocena sa id-om: {}", ocenaId);
         try {
	        if (!ocenaRepository.existsById(ocenaId)) {
	            throw new ResourceNotFoundException("Ocena sa ID " + ocenaId + " nije pronađena.");
	        }
	        ocenaRepository.deleteById(ocenaId);
         } catch (Exception e) {
             log.error("Greška pri brisanju ocene sa id-om: {}", e.getMessage());
             throw new RuntimeException("Greška pri brisanju ocene", e);
         }
        // Brisanje ocene
        ocenaRepository.deleteById(ocenaId);
    }
    
    @Override
    @Transactional
    public Map<String, Object> getOcenePoPredmetimaZaUcenika(Integer ucenikId) {
        // nadji sve ocene za datog učenika
        List<OcenaEntity> ocene = ocenaRepository.findAllByUcenikId(ucenikId);
        
        // Kreiramo mapu za rezultat
        Map<String, Object> rezultat = new HashMap<>();
        
        // Mapa za čuvanje lista ocena po predmetima
        Map<String, List<Map<String, Object>>> ocenePoPredmetima = new HashMap<>();
        // Mapa za čuvanje zaključnih ocena po predmetima
        Map<String, Integer> zakljucneOcene = new HashMap<>();

     // Iteriramo kroz sve ocene
        for (OcenaEntity ocena : ocene) {
            String nazivPredmeta = ocena.getPredmet().getNazivPredmeta();

            if (!ocenePoPredmetima.containsKey(nazivPredmeta)) {
                ocenePoPredmetima.put(nazivPredmeta, new ArrayList<>());
            }

            Map<String, Object> detaljiOcene = new HashMap<>();
            detaljiOcene.put("ocena", ocena.getVrednostOcene());
            detaljiOcene.put("datum", ocena.getDatum());
            detaljiOcene.put("aktivnost", ocena.getAktivnost().name());
            detaljiOcene.put("polugodiste", ocena.getPolugodiste().name());
            
            ocenePoPredmetima.get(nazivPredmeta).add(detaljiOcene);

            // Ako je ocena zaključna, postavljamo je za predmet
            if (ocena.getZakljucnaOcena() != null) {
                zakljucneOcene.put(nazivPredmeta, ocena.getZakljucnaOcena());
            }
        }

        rezultat.put("ocenePoPredmetima", ocenePoPredmetima);
        rezultat.put("zakljucneOcene", zakljucneOcene);

        return rezultat;
    }
    
    @Override
    @Transactional
    public ZakljucnaOcenaDTO dajZakljucnuOcenu(Integer ucenikId, Integer predmetId, Integer zakljucnaOcena) {
        
    	String ulogovaniKorisnikUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        NastavnikEntity ulogovaniNastavnik = nastavnikRepository.findByKorisnikNastavnikKorisnickoIme(ulogovaniKorisnikUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Nije pronađen nastavnik sa korisničkim imenom: " + ulogovaniKorisnikUsername));

    	// pronalaženje učenika i predmeta 
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        PredmetEntity predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));
     // Provera da li ulogovani nastavnik predaje dati predmet u odelenju učenika
        boolean predajeUOdeljenju = nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(ulogovaniNastavnik, ucenik.getOdelenje(), predmet);
        if (!predajeUOdeljenju) {
            throw new AccessDeniedException("Nastavnik ne predaje dati predmet u odelenju učenika.");
        }

        // zaključna ocena ide na nivou učenika i predmeta
        // pronađite sve ocene učenika za dati predmet
        List<OcenaEntity> ocene = ocenaRepository.findAllByUcenikAndPredmet(ucenik, predmet);

        if(ocene.isEmpty()) {
            throw new ResourceNotFoundException("Nema ocena za učenika za dati predmet.");
        }

        boolean vecPostojiZakljucnaOcena = ocenaRepository.existsByUcenikAndPredmetAndZakljucnaOcenaNotNull(ucenik, predmet);
        if(vecPostojiZakljucnaOcena) {
            throw new AccessDeniedException("Zaključna ocena za predmet je već postavljena. Ne možete dodati nove ocene.");
        }
        
        for(OcenaEntity ocena : ocene) {
            ocena.setZakljucnaOcena(zakljucnaOcena);
            ocenaRepository.save(ocena);
        }
        
        ZakljucnaOcenaDTO zakljucnaOcenaDTO = new ZakljucnaOcenaDTO();
        zakljucnaOcenaDTO.setPredmetNaziv(predmet.getNazivPredmeta());
        zakljucnaOcenaDTO.setZakljucnaOcena(zakljucnaOcena);
        return zakljucnaOcenaDTO;
    }
    
    @Override
    @Transactional
    public Double izracunajProsekZakljucnihOcenaZaUcenika(Integer ucenikId) {
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));

        RazredEntity razred = ucenik.getOdelenje().getRazred();
        List<PredmetEntity> predmeti = razred.getPredmetiPoRazredu();

        if (predmeti.isEmpty()) {
            throw new ResourceNotFoundException("Nema predmeta za dati razred.");
        }

        double sumaZakljucnihOcena = 0;
        int brojPredmetaSaZakljucnomOcenom = 0;

        for (PredmetEntity predmet : predmeti) {
            List<OcenaEntity> oceneUcenikaZaPredmet = ocenaRepository.findAllByUcenikAndPredmet(ucenik, predmet);
            for (OcenaEntity ocena : oceneUcenikaZaPredmet) {
                if (ocena.getZakljucnaOcena() != null) {
                    sumaZakljucnihOcena += ocena.getZakljucnaOcena();
                    brojPredmetaSaZakljucnomOcenom++;
                    break; // Pretpostavljamo da ima samo jednu zaključnu ocenu po predmetu
                }
            }
        }

        if (brojPredmetaSaZakljucnomOcenom != predmeti.size()) {
            throw new ResourceNotFoundException("Nisu sve zaključne ocene postavljene.");
        }

        double prosek = sumaZakljucnihOcena / brojPredmetaSaZakljucnomOcenom;
        prosek = Math.round(prosek * 100.0) / 100.0;
        return prosek;
    }
}