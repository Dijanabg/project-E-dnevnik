package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;

@Component
public class NastavnikMapper {
	@Autowired
	private KorisnikMapper korisnikMapper;
	public  NastavnikEntity toEntity(NastavnikDTO dto) {
		NastavnikEntity entity = new NastavnikEntity();
		entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setEmail(dto.getEmail());
        //nije mi prikazivalo podatke iz korisnika
        if (dto.getKorisnik() != null) {
            KorisnikEntity korisnikEntity = korisnikMapper.toEntity(dto.getKorisnik());
            entity.setKorisnik(korisnikEntity);
            
            // korisnikEntity.setNastavnik(entity); // dal ovo treba pojma nemam, mozda :)
        }
        return entity;
    }
	
	public  NastavnikDTO toDto(NastavnikEntity entity) {
		NastavnikDTO dto = new NastavnikDTO();
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
	
	public List<NastavnikEntity> toEntityList(List<NastavnikDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<NastavnikDTO> toDtoList(List<NastavnikEntity> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public void updateNastavnikEntityFromDto(NastavnikDTO dto, NastavnikEntity entity) {
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
