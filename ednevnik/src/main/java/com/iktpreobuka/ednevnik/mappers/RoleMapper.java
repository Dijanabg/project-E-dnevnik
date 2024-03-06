package com.iktpreobuka.ednevnik.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.dto.RoleDTO;

@Component
public class RoleMapper {
	public  RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
        entity.setName(dto.getName());
        
        return entity;
    }
	
	public  RoleDTO toDto(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
        dto.setName(entity.getName());
        
        return dto;
    }
	
	public List<RoleEntity> toEntityList(List<RoleDTO> dtos) {
	    List<RoleEntity> entities = new ArrayList<>();
	    for (RoleDTO dto : dtos) {
	        entities.add(toEntity(dto));
	    }
	    return entities;
	}
	
	public List<RoleDTO> toDtoList(List<RoleEntity> entities) {
	    List<RoleDTO> dtos = new ArrayList<>();
	    for (RoleEntity entity : entities) {
	        dtos.add(toDto(entity));
	    }
	    return dtos;
	}
}
