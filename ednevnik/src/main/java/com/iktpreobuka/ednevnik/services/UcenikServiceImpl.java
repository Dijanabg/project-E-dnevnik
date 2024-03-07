package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;

import com.iktpreobuka.ednevnik.repositories.RoleRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UcenikServiceImpl implements UcenikService{

	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
    private UcenikMapper ucenikMapper;
	
	@Autowired
    private KorisnikMapper korisnikMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	@Transactional
	public UcenikDTO saveUcenik(UcenikDTO ucenikDTO) {
		KorisnikEntity korisnikEntity =korisnikMapper.toEntity(ucenikDTO.getKorisnik());
		Integer roleId = ucenikDTO.getKorisnik().getRolaId();
    	if(roleId == null) {
    	    throw new IllegalArgumentException("Role ID must not be null");
    	}
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role nije pronađena!"));
        korisnikEntity.setRole(roleEntity);

        korisnikRepository.save(korisnikEntity);
        
        UcenikEntity ucenikEntity = ucenikMapper.toEntity(ucenikDTO);
        ucenikEntity.setKorisnik(korisnikEntity);
        ucenikEntity = ucenikRepository.save(ucenikEntity);
        
        return ucenikMapper.toDto(ucenikEntity);
	}

	@Override
	public List<UcenikDTO> findAll() {
		List<UcenikEntity> entities = (List<UcenikEntity>) ucenikRepository.findAll();
		return ucenikMapper.toDtoList(entities);
	}

	@Override
	public UcenikDTO findById(Integer id) {
		Optional<UcenikEntity> entityOptional = ucenikRepository.findById(id);
		return entityOptional.map(ucenikMapper::toDto).orElse(null);
	}

	@Override
	@Transactional
	public UcenikDTO updateUcenik(Integer ucenikId, UcenikDTO ucenikDTO) {
		UcenikEntity ucenik = ucenikRepository.findById(ucenikId).orElseThrow(()-> new EntityNotFoundException("Ucenik nije pronadjen!"));
		KorisnikEntity korisnikEntity = korisnikRepository.findById(ucenik.getKorisnik().getId())
                .orElseThrow(() -> new EntityNotFoundException("Korisnik nije pronađen!"));
        
        // azuriranje korisničkih podataka
        korisnikMapper.updateKorisnikEntityFromDto(ucenikDTO.getKorisnik(), korisnikEntity);
        Integer newRoleId = ucenikDTO.getKorisnik().getRolaId();
        if (newRoleId != null && !newRoleId.equals(korisnikEntity.getRole().getId())) {
            RoleEntity newRole = roleRepository.findById(newRoleId)
                    .orElseThrow(() -> new EntityNotFoundException("Role sa ID-om " + newRoleId + " nije pronađena!"));
            korisnikEntity.setRole(newRole);
        }
        korisnikRepository.save(korisnikEntity);
        
        //azuriranje ucenik podataka
        ucenikMapper.updateUcenikEntityFromDto(ucenikDTO, ucenik);
        ucenik.setKorisnik(korisnikEntity);
        ucenikRepository.save(ucenik);
        
        return ucenikMapper.toDto(ucenik);
	}

	@Override
	public void delete(Integer id) {
		ucenikRepository.deleteById(id);
	} 
	
	//brisanje ucenika a u isto vreme i korisnika
	@Override
	@Transactional
	public void deleteUcenikAndKorisnik(Integer id) {
	    // Pretpostavlja se da su ucenik i korisnik povezani preko istog ID-a
	    UcenikEntity ucenik = ucenikRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Ucenik not found with id " + id));
	    
	    // prvo brisem ucenika jer je korisnik roditelj
	    ucenikRepository.deleteById(id);
	    korisnikRepository.deleteById(ucenik.getKorisnik().getId());

	}
	
	@Override
	public List<UcenikDTO> findByImeAndPrezime(String ime, String prezime) {
		List<UcenikEntity> ucenici = ucenikRepository.findByImeAndPrezime(ime, prezime);
		return ucenikMapper.toDtoList(ucenici);
	}


}
