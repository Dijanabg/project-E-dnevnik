package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.repositories.RoleRepository;

@Component
public class KorisnikMapper {
	
	@Autowired
	private RoleRepository roleRepository; 
	
	public  KorisnikEntity toEntity(KorisnikDTO dto) {
		KorisnikEntity entity = new KorisnikEntity();
		entity.setId(dto.getId());
        entity.setKorisnickoIme(dto.getKorisnickoIme());
        entity.setSifra(dto.getSifra());
        
        if (dto.getRolaId() != null) {
            RoleEntity role = roleRepository.findById(dto.getRolaId()).orElse(null);
            entity.setRole(role);
        }
        
        return entity;
    }
	
	public  KorisnikDTO toDto(KorisnikEntity entity) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setId(entity.getId());
        dto.setKorisnickoIme(entity.getKorisnickoIme());
        dto.setSifra(entity.getSifra());
        
        if (entity.getRole() != null) {
            dto.setRolaId(entity.getRole().getId());
        }
      
        return dto;
    }
	
	public void updateKorisnikEntityFromDto(KorisnikDTO dto, KorisnikEntity entity) {
        if (dto == null || entity == null) return;

        if (dto.getKorisnickoIme() != null) {
            entity.setKorisnickoIme(dto.getKorisnickoIme());
        }
        if (dto.getSifra() != null) {
            entity.setSifra(dto.getSifra());
        }
        if (dto.getRolaId() != null) {
            RoleEntity role = roleRepository.findById(dto.getRolaId()).orElse(null);
            entity.setRole(role);
        }
    }
	
	public List<KorisnikDTO> toDtoList(List<KorisnikEntity> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
