package com.iktpreobuka.ednevnik.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;

@Component
public class OdelenjeMapper {
	public  OdelenjeEntity toEntity(OdelenjeDTO dto) {
		OdelenjeEntity entity = new OdelenjeEntity();
        entity.setAktivno(dto.isAktivno());
        entity.setOdelenje(dto.getOdelenje());
        
        return entity;
    }
	
	public  OdelenjeDTO toDto(OdelenjeEntity entity) {
		OdelenjeDTO dto = new OdelenjeDTO();
        dto.setId(entity.getId());
        dto.setAktivno(entity.isAktivno());
        dto.setOdelenje(entity.getOdelenje());
        dto.setSkolskaGodinaId(entity.getSkolskaGodina() != null ? entity.getSkolskaGodina().getId() : null);
        dto.setVerzija(entity.getVerzija());
        dto.setRazred(entity.getRazred() != null ? entity.getRazred().getId() : null);
        return dto;
    }
	public List<OdelenjeDTO> toDtoList(List<OdelenjeEntity> entities) {
        List<OdelenjeDTO> dtoList = new ArrayList<>();
        for (OdelenjeEntity entity : entities) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }
}
