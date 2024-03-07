package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;

@Component
public class UcenikMapper {
	
	@Autowired
	private KorisnikMapper korisnikMapper;
	
	public  UcenikEntity toEntity(UcenikDTO dto) {
		UcenikEntity entity = new UcenikEntity();
		entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setEmail(dto.getEmail());
        
        if (dto.getKorisnik() != null) {
            KorisnikEntity korisnikEntity = korisnikMapper.toEntity(dto.getKorisnik());
            entity.setKorisnik(korisnikEntity);
        }
        
        return entity;
    }
	
	public  UcenikDTO toDto(UcenikEntity entity) {
		UcenikDTO dto = new UcenikDTO();
		dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        dto.setEmail(entity.getEmail());
        
        if (entity.getKorisnik() != null) {
            KorisnikDTO korisnikDTO = korisnikMapper.toDto(entity.getKorisnik());
            dto.setKorisnik(korisnikDTO);
        }
        
        return dto;
    }
	public List<UcenikEntity> toEntityList(List<UcenikDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<UcenikDTO> toDtoList(List<UcenikEntity> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateUcenikEntityFromDto(UcenikDTO dto, UcenikEntity entity) {
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
        // nema azuriranja korisnika direktno
        // azuriranje korisnika preko korisnikMapper.updateKorisnikEntityFromDto metode
    }
}
