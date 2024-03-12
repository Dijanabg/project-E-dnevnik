package com.iktpreobuka.ednevnik.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.OcenaEntity;
import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.entities.enums.EAktivnostEntity;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;

@Component
public class OcenaMapper {
	
	public static OcenaDTO toDto(OcenaEntity ocenaEntity) {
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
            ocenaDTO.setAktivnost(ocenaEntity.getAktivnost().toString());
        }
        if(ocenaEntity.getSemestar() != null) {
            ocenaDTO.setPolugodiste(ocenaEntity.getSemestar().toString());
        }
        if(ocenaEntity.getOcenjivac() != null) {
            ocenaDTO.setOcenjivacId(ocenaEntity.getOcenjivac().getId());
        }
        return ocenaDTO;
    }

    // Konvertuje OcenaDTO u OcenaEntity
    public static OcenaEntity toEntity(OcenaDTO ocenaDTO) {
        if (ocenaDTO == null) {
            return null;
        }
        
        OcenaEntity ocenaEntity = new OcenaEntity();
        ocenaEntity.setId(ocenaDTO.getId());
        ocenaEntity.setVrednostOcene(ocenaDTO.getVrednostOcene());
        try {
            if(ocenaDTO.getDatum() != null && !ocenaDTO.getDatum().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                ocenaEntity.setDatum(sdf.parse(ocenaDTO.getDatum()));
            }
        } catch(ParseException e) {
            e.printStackTrace(); // U pravom projektu logger
        }
        if(ocenaDTO.getAktivnost() != null) {
            ocenaEntity.setAktivnost(EAktivnostEntity.valueOf(ocenaDTO.getAktivnost()));
        }
        if(ocenaDTO.getPolugodiste() != null) {
            ocenaEntity.setSemestar(EPolugodisteEntity.valueOf(ocenaDTO.getPolugodiste()));
        }
        // Napomena: za nastavnika i ucenika morate da ih dohvatite iz baze pre postavljanja
        return ocenaEntity;
    }
}
