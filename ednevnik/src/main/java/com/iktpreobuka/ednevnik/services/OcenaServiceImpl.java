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

        // Ako je sve u redu, nastavite sa kreiranjem i čuvanjem ocene
        OcenaEntity novaOcena = ocenaMapper.toEntity(ocenaDTO);
        novaOcena.setVrednostOcene(ocenaDTO.getVrednostOcene());
        EAktivnostEntity aktivnostEnum = EAktivnostEntity.valueOf(ocenaDTO.getAktivnost().toUpperCase());
        novaOcena.setAktivnost(aktivnostEnum);
        
        EPolugodisteEntity polugodisteEnum = EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste().toUpperCase());
        novaOcena.setPolugodiste(polugodisteEnum);
        
        novaOcena.setUcenik(ucenik);
        novaOcena.setPredmet(predmet);
        novaOcena.setOcenjivac(nastavnik);
        //kako setovati eAktivnost i ePolugodiste
        novaOcena.setDatum(new Date());
        novaOcena = ocenaRepository.save(novaOcena);

        return ocenaMapper.toDto(novaOcena);
    }
    
    @Override
    @Transactional
    public OcenaDTO updateOcenu(Integer ocenaId, OcenaDTO ocenaDTO) {
        // Pronađite postojeću ocenu po ID-u
        OcenaEntity postojecaOcena = ocenaRepository.findById(ocenaId)
                .orElseThrow(() -> new ResourceNotFoundException("Ocena nije pronađena."));

        // Proverite da li ucenik, nastavnik, i predmet postoje
        UcenikEntity ucenik = ucenikRepository.findById(ocenaDTO.getUcenikId())
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        NastavnikEntity nastavnik = nastavnikRepository.findById(ocenaDTO.getOcenjivacId())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen."));
        PredmetEntity predmet = predmetRepository.findById(ocenaDTO.getPredmetId())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));

        // Proverite da li nastavnik predaje dati predmet u odeljenju ucenika
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

        // Čuvanje ažurirane ocene
        OcenaEntity azuriranaOcena = ocenaRepository.save(postojecaOcena);

        // Pretvaranje u DTO i vraćanje
        return ocenaMapper.toDto(azuriranaOcena);
    }

    @Override
    @Transactional
    public void obrisiOcenu(Integer ocenaId) {
        // Proverite da li ocena sa datim ID-om postoji
        if (!ocenaRepository.existsById(ocenaId)) {
            throw new ResourceNotFoundException("Ocena sa ID " + ocenaId + " nije pronađena.");
        }

        // Brisanje ocene
        ocenaRepository.deleteById(ocenaId);
    }
    @Override
    @Transactional
    public Map<String, List<Integer>> getOcenePoPredmetimaZaUcenika(Integer ucenikId) {
        // Pronalazimo sve ocene za datog učenika
        List<OcenaEntity> ocene = ocenaRepository.findAllByUcenikId(ucenikId);
        
        // Kreiramo mapu za rezultat
        Map<String, List<Integer>> ocenePoPredmetima = new HashMap<>();
        
        // Iteriramo kroz sve ocene
        for (OcenaEntity ocena : ocene) {
            String nazivPredmeta = ocena.getPredmet().getNazivPredmeta();
            Integer vrednostOcene = ocena.getVrednostOcene();
            
            // Proveravamo da li u mapi već postoji lista ocena za taj predmet
            List<Integer> oceneZaPredmet = ocenePoPredmetima.get(nazivPredmeta);
            if (oceneZaPredmet == null) {
                // Ako lista ne postoji, kreiramo je i dodajemo trenutnu ocenu
                oceneZaPredmet = new ArrayList<>();
                oceneZaPredmet.add(vrednostOcene);
                ocenePoPredmetima.put(nazivPredmeta, oceneZaPredmet);
            } else {
                // Ako lista već postoji, jednostavno dodamo trenutnu ocenu u nju
                oceneZaPredmet.add(vrednostOcene);
            }
        }
        
        return ocenePoPredmetima;
    }
}