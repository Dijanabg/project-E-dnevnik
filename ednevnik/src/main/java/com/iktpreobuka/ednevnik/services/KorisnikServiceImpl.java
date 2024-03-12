package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KorisnikServiceImpl implements KorisnikService{
	@Autowired
    private KorisnikRepository korisnikRepository; // Pretpostavimo da imate repository za korisnika

	@Autowired
    private KorisnikMapper korisnikMapper;
	
	@Override
    public List<KorisnikDTO> findAll() {
        List<KorisnikEntity> korisnici = (List<KorisnikEntity>) korisnikRepository.findAll();
        return korisnikMapper.toDtoList(korisnici);
    }

    @Override
    public KorisnikDTO save(KorisnikDTO korisnikDto) {
        KorisnikEntity korisnikEntity = korisnikMapper.toEntity(korisnikDto);
        korisnikEntity = korisnikRepository.save(korisnikEntity);
        return korisnikMapper.toDto(korisnikEntity);
    }
    
	@Override
    public KorisnikDTO findById(Integer id) {
		Optional<KorisnikEntity> entity = korisnikRepository.findById(id);
        return entity.map(korisnikMapper::toDto).orElse(null); // Vraća korisnika na osnovu ID-a ili null ako korisnik nije pronađen
    }
	
	@Override
	public KorisnikDTO update(Integer id, KorisnikDTO korisnikDTO) {
	    // Pronađi korisnika po ID-u
	    KorisnikEntity korisnikEntity = korisnikRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Korisnik sa ID-om " + id + " nije pronađen."));

	    // Ažuriraj podatke korisnika
	    korisnikMapper.updateKorisnikEntityFromDto(korisnikDTO, korisnikEntity);

	    // Sačuvaj 
	    KorisnikEntity updatedEntity = korisnikRepository.save(korisnikEntity);

	    // Pretvori nazad u DTO i vrati ga
	    return korisnikMapper.toDto(updatedEntity);
	}
	
	@Override
    public void deleteById(Integer id) {
        korisnikRepository.deleteById(id);
    }
	
	@Override
	public KorisnikDTO findByKorisickoIme(String korisnickoIme) {
	    return korisnikRepository.findByKorisnickoIme(korisnickoIme)
	            .map(korisnikMapper::toDto)
	            .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa korisnickim imenom " + korisnickoIme + " nije pronađen."));
	}
}
