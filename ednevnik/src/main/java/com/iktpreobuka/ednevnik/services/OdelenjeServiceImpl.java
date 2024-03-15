package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikOdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.NastavnikMapper;
import com.iktpreobuka.ednevnik.mappers.OdelenjeMapper;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikOdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikPredmetRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.OdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;


@Service
public class OdelenjeServiceImpl implements OdelenjeService{

	@Autowired
    private OdelenjeRepository odelenjeRepository;

    @Autowired
    private OdelenjeMapper odelenjeMapper;
    
    @Autowired
    private UcenikMapper ucenikMapper;
    
    @Autowired
    private UcenikRepository ucenikRepository;


    @Autowired
    private RazredRepository razredRepository;

    @Autowired
    private NastavnikRepository nastavnikRepository;
    
    @Autowired 
    private NastavnikMapper nastavnikMapper;
    
    @Autowired
    private PredmetRepository predmetRepository;
    
    @Autowired
    private NastavnikOdelenjeRepository nastavnikOdelenjeRepository;
    
    @Autowired
    private NastavnikPredmetRepository nastavnikPredmetRepository;

    // Dodavanje novog odelenja
    @Override
    @Transactional
    public OdelenjeDTO createOdelenje(OdelenjeDTO odelenjeDTO) {
        OdelenjeEntity odelenje = odelenjeMapper.toEntity(odelenjeDTO);

        if (odelenjeDTO.getRazred() != null) {
            RazredEntity razred = razredRepository.findById(odelenjeDTO.getRazred())
                    .orElseThrow(() -> new RuntimeException("Razred not found"));
            odelenje.setRazred(razred);
        }

        if (odelenjeDTO.getRazredniStaresina() != null) {
            NastavnikEntity nastavnik = nastavnikRepository.findById(odelenjeDTO.getRazredniStaresina().getId())
                    .orElseThrow(() -> new RuntimeException("Nastavnik not found"));
            odelenje.setRazredniStaresina(nastavnik);
        }

        odelenje = odelenjeRepository.save(odelenje);
        return odelenjeMapper.toDto(odelenje);
    }

    // Ažuriranje postojećeg odelenja
    @Override
    @Transactional
    public OdelenjeDTO updateOdelenje(Integer id, OdelenjeDTO odelenjeDTO) {
        OdelenjeEntity odelenje = odelenjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Odelenje not found"));

        odelenje.setOdelenje(odelenjeDTO.getOdelenje());
        // Postavljanje razreda i razrednog starešine može se ažurirati slično kao kod dodavanja novog odelenja

        odelenje = odelenjeRepository.save(odelenje);
        return odelenjeMapper.toDto(odelenje);
    }
    
 // Pronalaženje odelenja po ID
    @Override
    @Transactional
    public OdelenjeDTO findOdelenjeById(Integer id) {
    	OdelenjeEntity odelenje = odelenjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Odelenje not found"));
        return odelenjeMapper.toDto(odelenje);
    }
    
    // Brisanje odelenja
    @Override
    @Transactional
    public void deleteOdelenje(Integer id) {
        odelenjeRepository.deleteById(id);
    }

    // Prikaz svih odelenja
    @Override
    @Transactional
    public List<OdelenjeDTO> findAllOdelenja() {
        List<OdelenjeEntity> odelenja = (List<OdelenjeEntity>) odelenjeRepository.findAll();
        
        return odelenjeMapper.toDtoList(odelenja);
    }

    //Dodavanje razrednog odelenju
    @Override
    @Transactional
    public void setRazredniStaresina(Integer odelenjeId, Integer nastavnikId) {
        OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
            .orElseThrow(() -> new ResourceNotFoundException("Odelenje nije pronađeno."));
        NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId)
            .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen."));
        
        odelenje.setRazredniStaresina(nastavnik); 
        odelenjeRepository.save(odelenje);
    }
    
    //prikaz razrednog nekog odelenja
    @Override
    @Transactional
    public NastavnikDTO getRazredniStaresinaOdOdelenja(Integer odelenjeId) {
        OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
            .orElseThrow(() -> new ResourceNotFoundException("Odelenje nije pronađeno."));
        
        NastavnikEntity razredniStaresina = odelenje.getRazredniStaresina();
        if (razredniStaresina == null) {
            throw new ResourceNotFoundException("Za ovo odelenje nije postavljen razredni starešina.");
        }

        return nastavnikMapper.toDto(razredniStaresina);
    }
    //Dodavanje učenika odelenju
    @Override
    @Transactional
    public UcenikDTO dodeliUcenikaOdelenju(Integer ucenikId, Integer odelenjeId) {
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));

        if (ucenik.getOdelenje() != null) {
            throw new IllegalStateException("Učenik je već dodeljen odelenju.");
        }

        OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
                .orElseThrow(() -> new ResourceNotFoundException("Odelenje nije pronađeno."));

        ucenik.setOdelenje(odelenje);
        // Ovde se čuva učenik sa novim odelenjem
        UcenikEntity sacuvaniUcenik = ucenikRepository.save(ucenik);

        // Pretvaranje entiteta učenika u DTO
        UcenikDTO ucenikDTO = ucenikMapper.toDto(sacuvaniUcenik);
        return ucenikDTO;
    }
    
    //Ukloni ucenika iz odelenja
    @Override
    public UcenikDTO ukloniUcenikaIzOdelenja(Integer ucenikId) {
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
            .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        
        ucenik.setOdelenje(null); // Uklanjanje učenika iz odelenja
        UcenikEntity updatedUcenik = ucenikRepository.save(ucenik);
        
        return ucenikMapper.toDto(updatedUcenik);
    }
    //vrati sve ucenike datog odelenja
    @Override
    @Transactional
    public List<UcenikDTO> findAllUceniciInOdelenje(Integer odelenjeId) {
        OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
            .orElseThrow(() -> new ResourceNotFoundException("Odelenje nije pronađeno."));
        
        return ucenikMapper.toDtoList(new ArrayList<>(odelenje.getUcenici()));
    }
    //dodati nastavnika odelenju koji predaje neki predmet
    @Override
    @Transactional
    public void dodajNastavnikaPredmetuUOdelenju(Integer nastavnikId, Integer predmetId, Integer odelenjeId) {
//    	// Provera da li postoji kombinacija nastavnik-predmet
//        NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId)
//            .orElseThrow(() -> new RuntimeException("Nastavnik not found"));
//        PredmetEntity predmet = predmetRepository.findById(predmetId)
//            .orElseThrow(() -> new RuntimeException("Predmet not found"));
//        OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
//            .orElseThrow(() -> new RuntimeException("Odelenje not found"));
//        
//        // Proveriti da li nastavnik već predaje dati predmet u odelenju
//        boolean alreadyExists = nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(nastavnik, odelenje, predmet);
//        
//        // Proveriti da li nastavnik može da predaje dati predmet
//        boolean canTeach = nastavnikPredmetRepository.existsByNastavnikIdAndPredmetId(nastavnikId, predmetId); // Ovo pretpostavlja postojanje takve provere
//        
//        if (!alreadyExists && canTeach) {
//            NastavnikOdelenjeEntity nastavnikOdelenje = new NastavnikOdelenjeEntity();
//            nastavnikOdelenje.setPredavac(nastavnik);
//            nastavnikOdelenje.setOdelenje(odelenje);
//            nastavnikOdelenje.setPredmet(predmet);
//
//            nastavnikOdelenjeRepository.save(nastavnikOdelenje);
//        } else if (!canTeach) {
//            throw new RuntimeException("Nastavnik nije kvalifikovan da predaje dati predmet");
//        } else {
//            throw new RuntimeException("Nastavnik već predaje ovaj predmet u odabranom odelenju");
//        }
    	NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId)
    	        .orElseThrow(() -> new RuntimeException("Nastavnik not found"));
    	    PredmetEntity predmet = predmetRepository.findById(predmetId)
    	        .orElseThrow(() -> new RuntimeException("Predmet not found"));
    	    OdelenjeEntity odelenje = odelenjeRepository.findById(odelenjeId)
    	        .orElseThrow(() -> new RuntimeException("Odelenje not found"));
    	    
    	    // Provera da li predmet odgovara razredu odelenja
    	    boolean isAppropriateGrade = predmet.getRazred() == odelenje.getRazred();
    	    if (!isAppropriateGrade) {
    	        throw new RuntimeException("Predmet nije namenjen razredu odelenja");
    	    }

    	    // Provera da li nastavnik već predaje dati predmet u odelenju
    	    boolean alreadyExists = nastavnikOdelenjeRepository.existsByPredavacAndOdelenjeAndPredmet(nastavnik, odelenje, predmet);
    	    
    	    // Provera da li nastavnik može da predaje dati predmet
    	    boolean canTeach = nastavnikPredmetRepository.existsByNastavnikIdAndPredmetId(nastavnikId, predmetId);
    	    
    	    if (!alreadyExists && canTeach) {
    	        NastavnikOdelenjeEntity nastavnikOdelenje = new NastavnikOdelenjeEntity();
    	        nastavnikOdelenje.setPredavac(nastavnik);
    	        nastavnikOdelenje.setOdelenje(odelenje);
    	        nastavnikOdelenje.setPredmet(predmet);
    	        nastavnikOdelenjeRepository.save(nastavnikOdelenje);
    	    } else if (!canTeach) {
    	        throw new RuntimeException("Nastavnik nije kvalifikovan da predaje dati predmet");
    	    } else {
    	        throw new RuntimeException("Nastavnik već predaje ovaj predmet u odabranom odelenju");
    	    }
    }

        //svi nastavnici i predmeti koje predju u nekom odelenju
    @Override
    @Transactional
    public List<NastavnikPredmetDTO> getPredmetiINastavniciZaOdelenje(Integer odelenjeId) {
    	 List<NastavnikOdelenjeEntity> nastavniciOdelenja = nastavnikOdelenjeRepository.findAllByOdelenjeId(odelenjeId);

    	    List<NastavnikPredmetDTO> predmetiNastavniciList = new ArrayList<>();
    	    for (NastavnikOdelenjeEntity nastavnikOdelenje : nastavniciOdelenja) {
    	        NastavnikEntity nastavnik = nastavnikOdelenje.getPredavac();
    	        PredmetEntity predmet = nastavnikOdelenje.getPredmet(); 
    	        if (nastavnik != null && predmet != null) {
    	            NastavnikPredmetDTO dto = new NastavnikPredmetDTO();
    	            dto.setId(nastavnikOdelenje.getId());
    	            dto.setNastavnikId(nastavnik.getId());
    	            dto.setNastavnikIme(nastavnik.getIme());
    	            dto.setNastavnikPrezime(nastavnik.getPrezime());
    	            dto.setPredmetId(predmet.getId());
    	            dto.setPredmetNaziv(predmet.getNazivPredmeta());
    	            //dto.setPredmetRazred(predmet.getRazred());
    	            predmetiNastavniciList.add(dto);
    	        }
    	    }

    	    return predmetiNastavniciList;
    }
}
