package com.iktpreobuka.ednevnik.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;

@Component
public class OdelenjeMapper {
	
	@Autowired
    private NastavnikMapper nastavnikMapper;
	
	public  OdelenjeEntity toEntity(OdelenjeDTO dto) {
		OdelenjeEntity entity = new OdelenjeEntity();
        entity.setOdelenje(dto.getOdelenje());
        return entity;
    }
	
	public  OdelenjeDTO toDto(OdelenjeEntity entity) {
		OdelenjeDTO dto = new OdelenjeDTO();
        dto.setId(entity.getId());
        dto.setOdelenje(entity.getOdelenje());
        dto.setVersion(entity.getVersion());
        dto.setRazred(entity.getRazred() != null ? entity.getRazred().getId() : null);
        
        if (entity.getRazredniStaresina() != null) {
            dto.setRazredniStaresina(nastavnikMapper.toDto(entity.getRazredniStaresina())); // Dodavanje informacija o razrednom starešini
        }
        
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
