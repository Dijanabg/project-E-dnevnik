package com.iktpreobuka.ednevnik.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;

@Component
public class PredmetMapper {
	
	@Autowired
	private RazredRepository razredRepository;
	public  PredmetEntity toEntity(PredmetDTO dto) {
		PredmetEntity entity = new PredmetEntity();
		entity.setId(dto.getId());
        entity.setNazivPredmeta(dto.getNazivPredmeta());
        entity.setCasovaNedeljno(dto.getCasovaNedeljno());
        entity.setVersion(dto.getVersion());
        
        if (dto.getRazredId() != null) {
            RazredEntity razred = razredRepository.findById(dto.getRazredId())
                    .orElseThrow(() -> new RuntimeException("Razred not found"));
            entity.setRazred(razred);
        }
        
        return entity;
    }
	
	public  PredmetDTO toDto(PredmetEntity entity) {
		PredmetDTO dto = new PredmetDTO();
		dto.setId(entity.getId());
		dto.setNazivPredmeta(entity.getNazivPredmeta());
		dto.setCasovaNedeljno(entity.getCasovaNedeljno());
		dto.setVersion(entity.getVersion());
		
		if (entity.getRazred() != null) {
	        dto.setRazredId(entity.getRazred().getId());
	    }
		
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
