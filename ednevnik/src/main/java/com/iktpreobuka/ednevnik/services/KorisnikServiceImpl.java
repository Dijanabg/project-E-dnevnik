package com.iktpreobuka.ednevnik.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService{
	
	private static final Logger log = LoggerFactory.getLogger(KorisnikServiceImpl.class);
	
	@Autowired
    private KorisnikRepository korisnikRepository; 

	@Autowired
    private KorisnikMapper korisnikMapper;
	
	@Override
    public List<KorisnikDTO> findAll() {
        List<KorisnikEntity> korisnici = (List<KorisnikEntity>) korisnikRepository.findAll();
        return korisnikMapper.toDtoList(korisnici);
    }

    @Override
    public KorisnikDTO save(KorisnikDTO korisnikDto) {
    	log.info("Kreiranje korisnika: {}", korisnikDto.getKorisnickoIme());
    	KorisnikEntity korisnikEntity = korisnikMapper.toEntity(korisnikDto);
        korisnikEntity = korisnikRepository.save(korisnikEntity);
        return korisnikMapper.toDto(korisnikEntity);
    }
    
	@Override
    public KorisnikDTO findById(Integer id) {
		Optional<KorisnikEntity> entity = korisnikRepository.findById(id);
        return entity.map(korisnikMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Korisnik nije pronađen."));
    }
	
	@Override
	public KorisnikDTO update(Integer id, KorisnikDTO korisnikDTO) {
		log.info("Ažuriranje korisnika sa ID: {}", id);
	    KorisnikEntity korisnikEntity = korisnikRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Korisnik nije pronađen."));

	    korisnikMapper.updateKorisnikEntityFromDto(korisnikDTO, korisnikEntity);

	    KorisnikEntity updatedEntity = korisnikRepository.save(korisnikEntity);

	    return korisnikMapper.toDto(updatedEntity);
	}
	
	@Override
    public void deleteById(Integer id) {
		log.info("Brisanje korisnika sa ID: {}", id);
        korisnikRepository.deleteById(id);
    }
	
	@Override
	public KorisnikDTO findByKorisickoIme(String korisnickoIme) {
	    return korisnikRepository.findByKorisnickoIme(korisnickoIme)
	            .map(korisnikMapper::toDto)
	            .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa korisnickim imenom " + korisnickoIme + " nije pronađen."));
	}
}
