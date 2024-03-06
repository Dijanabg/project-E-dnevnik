package com.iktpreobuka.ednevnik.mappers;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;

@Component
public class RazredMapper {
	public  RazredEntity toEntity(RazredDTO dto) {
		RazredEntity entity = new RazredEntity();
		entity.setId(dto.getId());
        entity.setRazred(dto.getRazred());
        
        return entity;
    }
	
	public  RazredDTO toDto(RazredEntity entity) {
		RazredDTO dto = new RazredDTO();
		dto.setId(entity.getId());
        dto.setRazred(entity.getRazred());
        
        return dto;
    }
}
