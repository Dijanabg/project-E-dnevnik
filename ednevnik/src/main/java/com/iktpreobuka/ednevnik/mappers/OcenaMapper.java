package com.iktpreobuka.ednevnik.mappers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.OcenaEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;

@Component
public class OcenaMapper {
	
	@Autowired 
	private PredmetRepository predmetRepository;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired NastavnikRepository nastavnikRepository;
	public OcenaDTO toDto(OcenaEntity ocenaEntity) {
        if (ocenaEntity == null) {
            return null;
        }
        
        OcenaDTO ocenaDTO = new OcenaDTO();
        ocenaDTO.setId(ocenaEntity.getId());
        ocenaDTO.setVrednostOcene(ocenaEntity.getVrednostOcene());
        if(ocenaEntity.getDatum() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            ocenaDTO.setDatum(sdf.format(ocenaEntity.getDatum()));
        }
        if(ocenaEntity.getAktivnost() != null) {
            ocenaDTO.setAktivnost(ocenaEntity.getAktivnost());
        }
        if(ocenaEntity.getPolugodiste() != null) {
            ocenaDTO.setPolugodiste(ocenaEntity.getPolugodiste().toString());
        }
        if(ocenaEntity.getOcenjivac() != null) {
            ocenaDTO.setOcenjivacId(ocenaEntity.getOcenjivac().getId());
        }
        if(ocenaEntity.getUcenik() != null) {
            ocenaDTO.setUcenikId(ocenaEntity.getUcenik().getId());
        }
        if (ocenaEntity.getUcenik() != null) {
            String ucenikImePrezime = String.format("%s %s", 
                    ocenaEntity.getUcenik().getIme(), 
                    ocenaEntity.getUcenik().getPrezime());
            ocenaDTO.setUcenikImePrezime(ucenikImePrezime);
        }

        if (ocenaEntity.getOcenjivac() != null) {
            String nastavnikImePrezime = String.format("%s %s", 
                    ocenaEntity.getOcenjivac().getIme(), 
                    ocenaEntity.getOcenjivac().getPrezime());
            ocenaDTO.setNastavnikImePrezime(nastavnikImePrezime);
        }
        if(ocenaEntity.getPredmet() != null) {
            ocenaDTO.setPredmetId(ocenaEntity.getPredmet().getId());
            ocenaDTO.setPredmetNaziv(ocenaEntity.getPredmet().getNazivPredmeta());
        }

        if (ocenaEntity.getZakljucnaOcena() == null) {
            ocenaDTO.setZakljucnaOcenaPoruka("Ocene još nisu zaključene");
        } else {
            ocenaDTO.setZakljucnaOcena(ocenaEntity.getZakljucnaOcena());
        }
        return ocenaDTO;
        
    }

    public OcenaEntity toEntity(OcenaDTO ocenaDTO, NastavnikEntity ocenjivac) {
        if (ocenaDTO == null) {
            return null;
        }
        
        OcenaEntity ocenaEntity = new OcenaEntity();
        ocenaEntity.setId(ocenaDTO.getId());
        ocenaEntity.setVrednostOcene(ocenaDTO.getVrednostOcene());
     
        ocenaEntity.setDatum(new Date());
        ocenaEntity.setZakljucnaOcena(ocenaDTO.getZakljucnaOcena());

        if(ocenaDTO.getAktivnost() != null) {
            ocenaEntity.setAktivnost(ocenaDTO.getAktivnost());
        }
        if(ocenaDTO.getPolugodiste() != null) {
            ocenaEntity.setPolugodiste(EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste()));
        }
        
     
        PredmetEntity predmetEntity = predmetRepository.findById(ocenaDTO.getPredmetId())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet nije pronađen."));
        UcenikEntity ucenikEntity = ucenikRepository.findById(ocenaDTO.getUcenikId())
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));

        if(ocenaDTO.getOcenjivacId() != null) {
            NastavnikEntity ocenjivacEntity = nastavnikRepository.findById(ocenaDTO.getOcenjivacId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen."));
            ocenaEntity.setOcenjivac(ocenjivacEntity);
        }
        ocenaEntity.setPredmet(predmetEntity); 
        ocenaEntity.setUcenik(ucenikEntity); 
        ocenaEntity.setOcenjivac(ocenjivac);
        
        return ocenaEntity;
    }
}
