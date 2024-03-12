package com.iktpreobuka.ednevnik.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikOdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikOdelenjeDTO;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.OdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;

@Component
public class NastavnikOdelenjeMapper {
	
	@Autowired
    private NastavnikRepository nastavnikRepository;
    
    @Autowired
    private OdelenjeRepository odelenjeRepository;
    
    @Autowired
    private PredmetRepository predmetRepository;

    public NastavnikOdelenjeEntity toEntity(NastavnikOdelenjeDTO dto) {
        NastavnikOdelenjeEntity entity = new NastavnikOdelenjeEntity();

        if (dto.getNastavnikId() != null) {
            NastavnikEntity nastavnik = nastavnikRepository.findById(dto.getNastavnikId())
                    .orElseThrow(() -> new RuntimeException("Nastavnik not found with id " + dto.getNastavnikId()));
            entity.setPredavac(nastavnik);
        }

        if (dto.getOdeljenjeId() != null) {
            OdelenjeEntity odelenje = odelenjeRepository.findById(dto.getOdeljenjeId())
                    .orElseThrow(() -> new RuntimeException("Odelenje not found with id " + dto.getOdeljenjeId()));
            entity.setOdelenje(odelenje);
        }

        if (dto.getPredmetId() != null) {
            PredmetEntity predmet = predmetRepository.findById(dto.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet not found with id " + dto.getPredmetId()));
            entity.setPredmet(predmet);
        }

        return entity;
    }
    
	public NastavnikOdelenjeDTO toDto(NastavnikOdelenjeEntity nastavnikOdelenje) {
        NastavnikOdelenjeDTO dto = new NastavnikOdelenjeDTO();
        dto.setId(nastavnikOdelenje.getId());
        dto.setNastavnikId(nastavnikOdelenje.getPredavac().getId());
        dto.setNastavnikIme(nastavnikOdelenje.getPredavac().getIme() + " " + nastavnikOdelenje.getPredavac().getPrezime());
        dto.setOdeljenjeId(nastavnikOdelenje.getOdelenje().getId());
        // dodavanje informacija o predmetu
        if (nastavnikOdelenje.getPredmet() != null) {
            dto.setPredmetId(nastavnikOdelenje.getPredmet().getId());
            dto.setPredmetNaziv(nastavnikOdelenje.getPredmet().getNazivPredmeta());
        }

        return dto;
    }
}
