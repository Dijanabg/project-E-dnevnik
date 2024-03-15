package com.iktpreobuka.ednevnik.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;

@Component
public class UcenikMapper {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired RoditeljMapper roditeljMapper;
	
	public  UcenikEntity toEntity(UcenikDTO dto) {
		UcenikEntity entity = new UcenikEntity();
		entity.setId(dto.getId());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setEmail(dto.getEmail());
        
        if (dto.getKorisnikId() != null) {
            KorisnikEntity korisnik = korisnikRepository.findById(dto.getKorisnikId()).
            		orElseThrow(() -> new ResourceNotFoundException("Korisnik sa ID " + dto.getKorisnikId() + " nije pronađena."));
            entity.setKorisnikUcenik(korisnik);
        }
        
        return entity;
    }
	
	public  UcenikDTO toDto(UcenikEntity entity) {
		UcenikDTO dto = new UcenikDTO();
		dto.setId(entity.getId());
        dto.setIme(entity.getIme());
        dto.setPrezime(entity.getPrezime());
        dto.setEmail(entity.getEmail());
        
        if (entity.getKorisnikUcenik() != null) {
            dto.setKorisnikId(entity.getKorisnikUcenik().getId());
        }
        if (entity.getRoditelj() != null) {
            dto.setRoditelj(roditeljMapper.toDto(entity.getRoditelj())); // Dodavanje informacija o roditelju
        }
        if (entity.getOdelenje() != null) {
            dto.setOdelenje(entity.getOdelenje().getOdelenje());
         // Dodajemo informaciju o razredu 
            if (entity.getOdelenje().getRazred() != null) {
                dto.setRazred(entity.getOdelenje().getRazred().getRazred()); // Pretpostavljam da želite broj razreda
            }
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
