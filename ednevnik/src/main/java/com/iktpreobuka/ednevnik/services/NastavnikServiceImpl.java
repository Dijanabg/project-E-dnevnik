package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.mappers.NastavnikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class NastavnikServiceImpl implements NastavnikService{
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
    private NastavnikMapper nastavnikMapper;
	
	@Autowired
    private KorisnikMapper korisnikMapper;
	
	@Autowired
	private RoleRepository roleRepository;

    @Override
    @Transactional
    public NastavnikDTO saveNastavnik(NastavnikDTO nastavnikDTO) {
    	KorisnikEntity korisnikEntity = korisnikMapper.toEntity(nastavnikDTO.getKorisnik());
    	Integer roleId = nastavnikDTO.getKorisnik().getRolaId();
    	if(roleId == null) {
    	    throw new IllegalArgumentException("Role ID must not be null");
    	}
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role nije pronađena!"));
        korisnikEntity.setRole(roleEntity);

        korisnikRepository.save(korisnikEntity);
        
        NastavnikEntity nastavnikEntity = nastavnikMapper.toEntity(nastavnikDTO);
        
        nastavnikEntity.setKorisnik(korisnikEntity);
        
        nastavnikEntity = nastavnikRepository.save(nastavnikEntity);
        return nastavnikMapper.toDto(nastavnikEntity);
    }

    @Override
    @Transactional
    public List<NastavnikDTO> findAll() {
        List<NastavnikEntity> entities = (List<NastavnikEntity>) nastavnikRepository.findAll();
        return nastavnikMapper.toDtoList(entities);
    }

    @Override
    public NastavnikDTO updateNastavnik(Integer nastavnikId, NastavnikDTO nastavnikDTO) {
        NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId)
                .orElseThrow(() -> new EntityNotFoundException("Nastavnik nije pronađen!"));

        KorisnikEntity korisnikEntity = korisnikRepository.findById(nastavnik.getKorisnik().getId())
                .orElseThrow(() -> new EntityNotFoundException("Korisnik nije pronađen!"));
        
        // Ažuriranje korisničkih podataka
        korisnikMapper.updateKorisnikEntityFromDto(nastavnikDTO.getKorisnik(), korisnikEntity);
        Integer newRoleId = nastavnikDTO.getKorisnik().getRolaId();
        if (newRoleId != null && !newRoleId.equals(korisnikEntity.getRole().getId())) {
            RoleEntity newRole = roleRepository.findById(newRoleId)
                    .orElseThrow(() -> new EntityNotFoundException("Role sa ID-om " + newRoleId + " nije pronađena!"));
            korisnikEntity.setRole(newRole);
        }
        korisnikRepository.save(korisnikEntity);

        // Ažuriranje nastavničkih podataka
        nastavnikMapper.updateNastavnikEntityFromDto(nastavnikDTO, nastavnik);
        nastavnik.setKorisnik(korisnikEntity);
        nastavnikRepository.save(nastavnik);
        
        return nastavnikMapper.toDto(nastavnik);
    }
    
    @Override
    @Transactional
    public NastavnikDTO findById(Integer id) {
        Optional<NastavnikEntity> entity = nastavnikRepository.findById(id);
        return entity.map(nastavnikMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        nastavnikRepository.deleteById(id);
    }
    
    @Override
    public List<NastavnikDTO> findByImeAndPrezime(String ime, String prezime) {
    	List<NastavnikEntity> nastavnici = nastavnikRepository.findByImeAndPrezime(ime, prezime);
        return nastavnikMapper.toDtoList(nastavnici);
    }
    
    //da li ovde i ovo???
    // dodeljivanje nastavnika odelenju
    // uklanjanje dodeljenog nastavnika odelenju
    // pregled nastavnika sa listom predmeta koje predae i u kojim odelenjima
}
