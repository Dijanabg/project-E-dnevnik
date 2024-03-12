package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.NastavnikMapper;
import com.iktpreobuka.ednevnik.mappers.OdelenjeMapper;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.OdelenjeRepository;
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
        //dodati po razredu, dal uopste treba ovo
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
}
