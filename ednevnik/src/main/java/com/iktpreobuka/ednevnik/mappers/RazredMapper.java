package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.repositories.SkolskaGodinaRepository;

@Component
public class RazredMapper {
	
	@Autowired
	private SkolskaGodinaRepository skolskaGodinaRepository;
	public  RazredEntity toEntity(RazredDTO dto) {
		RazredEntity entity = new RazredEntity();
		entity.setId(dto.getId());
        entity.setRazred(dto.getRazred());
        if (dto.getSkolskaGodinaId() != null) {
            SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(dto.getSkolskaGodinaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Školska godina sa ID " + dto.getSkolskaGodinaId() + " nije pronađena."));
            entity.setSkolskaGodina(skolskaGodina);
        }
        return entity;
    }
	
	public  RazredDTO toDto(RazredEntity entity) {
		RazredDTO dto = new RazredDTO();
		dto.setId(entity.getId());
        dto.setRazred(entity.getRazred());
        if (entity.getSkolskaGodina() != null) {
            dto.setSkolskaGodinaId(entity.getSkolskaGodina().getId());
        }
        return dto;
    }
	public List<RazredDTO> toDtoList(List<RazredEntity> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }
}
