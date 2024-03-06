package com.iktpreobuka.ednevnik.mappers;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;

@Component
public class SkolskaGodinaMapper {
	public  SkolskaGodinaEntity toEntity(SkolskaGodinaDTO dto) {
		SkolskaGodinaEntity entity = new SkolskaGodinaEntity();
		entity.setId(dto.getId());
        entity.setOznaka(dto.getOznaka());
        
        return entity;
    }
	
	public  SkolskaGodinaDTO toDto(SkolskaGodinaEntity entity) {
		SkolskaGodinaDTO dto = new SkolskaGodinaDTO();
		dto.setId(entity.getId());
        dto.setOznaka(entity.getOznaka());
        
        return dto;
    }
}
