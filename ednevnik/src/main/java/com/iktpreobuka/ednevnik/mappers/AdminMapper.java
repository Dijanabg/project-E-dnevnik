package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.AdminEntity;
import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;
@Component
public class AdminMapper {
	@Autowired
	private KorisnikRepository korisnikRepository;

	public  AdminEntity toEntity(AdminDTO dto) {
		AdminEntity entity = new AdminEntity();
		entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        
        
        if (dto.getKorisnikId() != null) {
            KorisnikEntity korisnik = korisnikRepository.findById(dto.getKorisnikId()).orElse(null);
            entity.setKorisnikAdmin(korisnik);
        }
        return entity;
    }
	
	public  AdminDTO toDto(AdminEntity entity) {
		AdminDTO dto = new AdminDTO();
		dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        
        
        if (entity.getKorisnikAdmin() != null) {
            dto.setKorisnikId(entity.getKorisnikAdmin().getId());
        }
        
        return dto;
    }
	
	public List<AdminEntity> toEntityList(List<AdminDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<AdminDTO> toDtoList(List<AdminEntity> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public void updateNastavnikEntityFromDto(AdminDTO dto, AdminEntity entity) {
        if (dto == null || entity == null) return;

        if (dto.getIme() != null) {
            entity.setIme(dto.getIme());
        }
        if (dto.getPrezime() != null) {
            entity.setPrezime(dto.getPrezime());
        }
       
    }
}
