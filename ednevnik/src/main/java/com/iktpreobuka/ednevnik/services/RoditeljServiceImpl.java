package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.RoditeljEntity;
import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.mappers.RoditeljMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;
import com.iktpreobuka.ednevnik.repositories.RoditeljRepository;
import com.iktpreobuka.ednevnik.repositories.RoleRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RoditeljServiceImpl implements RoditeljService{

	@Autowired
	private RoditeljRepository roditeljRepository;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
    private RoditeljMapper roditeljMapper;
	
	@Autowired
    private KorisnikMapper korisnikMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
    @Transactional
    public List<RoditeljDTO> findAll() {
        List<RoditeljEntity> entities = (List<RoditeljEntity>) roditeljRepository.findAll();
        return roditeljMapper.toDtoList(entities);
    }

	@Override
	public RoditeljDTO saveRoditelj(RoditeljDTO roditeljDTO) {
		KorisnikEntity korisnikEntity = korisnikMapper.toEntity(roditeljDTO.getKorisnik());
    	Integer roleId = roditeljDTO.getKorisnik().getRolaId();
    	if(roleId == null) {
    	    throw new IllegalArgumentException("Role ID must not be null");
    	}
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role nije pronađena!"));
        korisnikEntity.setRole(roleEntity);

        korisnikRepository.save(korisnikEntity);
        
        RoditeljEntity roditeljEntity = roditeljMapper.toEntity(roditeljDTO);
        
        roditeljEntity.setKorisnik(korisnikEntity); // Postavljanje veze sa korisničkim entitetom
        
        roditeljRepository.save(roditeljEntity);
     
        // povezivanje roditelja i njihove dece
        if (roditeljDTO.getDeteIds() != null && !roditeljDTO.getDeteIds().isEmpty()) {
            for (Integer deteId : roditeljDTO.getDeteIds()) {
                UcenikEntity ucenikEntity = ucenikRepository.findById(deteId)
                    .orElseThrow(() -> new EntityNotFoundException("Učenik sa ID-em " + deteId + " nije pronađen!"));
                ucenikEntity.setRoditelj(roditeljEntity); // Pretpostavimo da postoji setter u UcenikEntity za postavljanje roditelja
                ucenikRepository.save(ucenikEntity);
            }
        }
        
        return roditeljMapper.toDto(roditeljEntity);
	}

	@Override
	public RoditeljDTO findById(Integer id) {
		Optional<RoditeljEntity> entityOptional = roditeljRepository.findById(id);
		return entityOptional.map(roditeljMapper::toDto).orElse(null);
	}

	@Override
	@Transactional
	public RoditeljDTO updateNastavnik(Integer roditeljId, RoditeljDTO roditeljDTO) {
		 RoditeljEntity roditelj = roditeljRepository.findById(roditeljId)
	            .orElseThrow(() -> new EntityNotFoundException("Roditelj sa ID-em " + roditeljId + " nije pronađen!"));
	    
	    // Ažuriranje korisničkih podataka
		 KorisnikEntity korisnikEntity = korisnikRepository.findById(roditelj.getKorisnik().getId())
	                .orElseThrow(() -> new EntityNotFoundException("Korisnik nije pronađen!"));
	        
	    // Ažuriranje korisničkih podataka
	     korisnikMapper.updateKorisnikEntityFromDto(roditeljDTO.getKorisnik(), korisnikEntity);
	     Integer newRoleId = roditeljDTO.getKorisnik().getRolaId();
	     if (newRoleId != null && !newRoleId.equals(korisnikEntity.getRole().getId())) {
	         RoleEntity newRole = roleRepository.findById(newRoleId)
	                    .orElseThrow(() -> new EntityNotFoundException("Role sa ID-om " + newRoleId + " nije pronađena!"));
	         korisnikEntity.setRole(newRole);
	     }
	     korisnikRepository.save(korisnikEntity);
	    
	    // Ažuriranje veza sa decom
	     for (Integer deteId : roditeljDTO.getDeteIds()) {
	         UcenikEntity dete = ucenikRepository.findById(deteId)
	                 .orElseThrow(() -> new EntityNotFoundException("Učenik sa ID-em " + deteId + " nije pronađen!"));
	         roditelj.addDete(dete); // Pretpostavka da postoji metoda addDete u RoditeljEntity
	     }
	     roditelj = roditeljRepository.save(roditelj);
	    // Vraćanje ažuriranog DTO objekta
	    return roditeljMapper.toDto(roditelj);
	}

	@Override
	public void delete(Integer id) {
		roditeljRepository.deleteById(id);
	}

	@Override
	public List<RoditeljDTO> findByImeAndPrezime(String ime, String prezime) {
		List<RoditeljEntity> roditeljiEntities = roditeljRepository.findByImeAndPrezime(ime, prezime);
		return roditeljMapper.toDtoList(roditeljiEntities);
	}
}
