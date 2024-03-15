package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.RoditeljEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;

@Component
public class RoditeljMapper {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public RoditeljEntity toEntity(RoditeljDTO dto) {
		RoditeljEntity entity = new RoditeljEntity();
		entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setEmail(dto.getEmail());
        if (dto.getKorisnikId() != null) {
            KorisnikEntity korisnik = korisnikRepository.findById(dto.getKorisnikId()).orElse(null);
            entity.setKorisnikRoditelj(korisnik);
        }
        return entity;
	}
	
	public RoditeljDTO toDto(RoditeljEntity entity) {
		RoditeljDTO dto = new RoditeljDTO();
		dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        dto.setEmail(entity.getEmail());
        
        if (entity.getKorisnikRoditelj() != null) {
            dto.setKorisnikId(entity.getKorisnikRoditelj().getId());
        }
        if(entity.getDete() != null && !entity.getDete().isEmpty()) {
            List<Integer> deteIds = entity.getDete().stream().map(UcenikEntity::getId).collect(Collectors.toList());
            dto.setDeteIds(deteIds);
        }
        return dto;
	}
	
	public List<RoditeljEntity> toEntityList(List<RoditeljDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<RoditeljDTO> toDtoList(List<RoditeljEntity> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public void updateRoditeljEntityFromDto(RoditeljDTO dto, RoditeljEntity entity) {
        if (dto == null || entity == null) return;

        if (dto.getIme() != null) {
            entity.setIme(dto.getIme());
        }
        if (dto.getPrezime() != null) {
            entity.setPrezime(dto.getPrezime());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
    }
}
