package com.iktpreobuka.ednevnik.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
@Component
public class PredmetMapper {
	public  PredmetEntity toEntity(PredmetDTO dto) {
		PredmetEntity entity = new PredmetEntity();
		entity.setId(dto.getId());
        entity.setNazivPredmeta(dto.getNazivPredmeta());
        entity.setCasovaNedeljno(dto.getCasovaNedeljno());
        entity.setEpolugodiste(EPolugodisteEntity.valueOf(dto.getEpolugodiste()));
        entity.setVersion(dto.getVersion());
        return entity;
    }
	
	public  PredmetDTO toDto(PredmetEntity entity) {
		PredmetDTO dto = new PredmetDTO();
		dto.setId(entity.getId());
		dto.setNazivPredmeta(entity.getNazivPredmeta());
		dto.setCasovaNedeljno(entity.getCasovaNedeljno());
		
		if (entity.getEpolugodiste() != null) {
            dto.setEpolugodiste(entity.getEpolugodiste().toString()); // Ako je epolugodiste enum, prebacujemo u String
        }
		dto.setVersion(entity.getVersion());
        return dto;
    }
	public List<PredmetDTO> toDtoList(List<PredmetEntity> entities) {
		List<PredmetDTO> dtoList = new ArrayList<>();
        for (PredmetEntity entity : entities) {
            PredmetDTO dto = toDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
