package com.iktpreobuka.ednevnik.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.OcenaEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
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

    @Override
    @Transactional
    public OcenaDTO dodajOcenu(OcenaDTO ocenaDTO) {
    	UcenikEntity ucenik = ucenikRepository.findById(ocenaDTO.getUcenikId())
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        NastavnikEntity nastavnik = nastavnikRepository.findById(ocenaDTO.getOcenjivacId())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen."));
        PredmetEntity predmet = predmetRepository.findById(ocenaDTO.getPredmetId())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));
        OdelenjeEntity odelenje = odelenjeRepository.findById(ucenik.getOdelenje().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Odeljenje nije pronađeno."));

        if (!nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(nastavnik, odelenje, predmet)) {
            throw new ResourceNotFoundException("Nastavnik ne predaje dati predmet u odeljenju učenika.");
        }

        OcenaEntity novaOcena = ocenaMapper.toEntity(ocenaDTO);
        novaOcena.setVrednostOcene(ocenaDTO.getVrednostOcene());
        EAktivnostEntity aktivnostEnum = EAktivnostEntity.valueOf(ocenaDTO.getAktivnost().toUpperCase());
        novaOcena.setAktivnost(aktivnostEnum);
        
        EPolugodisteEntity polugodisteEnum = EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste().toUpperCase());
        novaOcena.setPolugodiste(polugodisteEnum);
        
        novaOcena.setUcenik(ucenik);
        novaOcena.setPredmet(predmet);
        novaOcena.setOcenjivac(nastavnik);
        novaOcena.setDatum(new Date());
        novaOcena = ocenaRepository.save(novaOcena);

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
        postojecaOcena.setAktivnost(EAktivnostEntity.valueOf(ocenaDTO.getAktivnost().toUpperCase()));
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
        if (!ocenaRepository.existsById(ocenaId)) {
            throw new ResourceNotFoundException("Ocena sa ID " + ocenaId + " nije pronađena.");
        }

        // Brisanje ocene
        ocenaRepository.deleteById(ocenaId);
    }
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
        //odustala od bilo kog drugacijeg ispisa jer nismo radili 
        //ni mape ni mapiranje objekata ni ko zna sta sto bi ovde trebalo primeniti
        
        
    }
    
    @Override
    @Transactional
    public ZakljucnaOcenaDTO dajZakljucnuOcenu(Integer ucenikId, Integer predmetId, Integer zakljucnaOcena) {
        // pronalaženje učenika i predmeta 
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        PredmetEntity predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));
        
        // zaključna ocena ide na nivou učenika i predmeta
        // pronađite sve ocene učenika za dati predmet
        List<OcenaEntity> ocene = ocenaRepository.findAllByUcenikAndPredmet(ucenik, predmet);

        if(ocene.isEmpty()) {
            throw new ResourceNotFoundException("Nema ocena za učenika za dati predmet.");
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
        List<OcenaEntity> sveOceneUcenika = ocenaRepository.findAllByUcenikId(ucenikId);
        Map<Integer, Integer> zakljucneOcenePoPredmetima = new HashMap<>(); // predmet id -> zakljucna ocena

        for (OcenaEntity ocena : sveOceneUcenika) {
            if (ocena.getZakljucnaOcena() != null) {
                zakljucneOcenePoPredmetima.put(ocena.getPredmet().getId(), ocena.getZakljucnaOcena());
            }
        }

        // Proveravamo da li je za svaki predmet postavljena zaključna ocena
        long brojPredmeta = predmetRepository.count();
        if (zakljucneOcenePoPredmetima.size() != brojPredmeta) {
            throw new ResourceNotFoundException("Nisu sve zaključne ocene postavljene.");
        }

        // Izračunavanje proseka
        int sumaZakljucnihOcena = 0;
        for (Integer ocena : zakljucneOcenePoPredmetima.values()) {
            sumaZakljucnihOcena += ocena;
        }

        return (double) sumaZakljucnihOcena / zakljucneOcenePoPredmetima.size();
    }
}