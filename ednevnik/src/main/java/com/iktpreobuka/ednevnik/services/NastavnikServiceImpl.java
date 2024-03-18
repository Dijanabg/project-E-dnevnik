package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.NastavnikMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;


@Service
public class NastavnikServiceImpl implements NastavnikService{
	private static final Logger log = LoggerFactory.getLogger(NastavnikServiceImpl.class);
	@Autowired
    private NastavnikRepository nastavnikRepository;

    @Autowired
    NastavnikMapper nastavnikMapper;

    @Override
    public List<NastavnikDTO> findAll() {
    	List<NastavnikEntity> nastavnici = (List<NastavnikEntity>) nastavnikRepository.findAll();
        return nastavnikMapper.toDtoList(nastavnici);
    }

    @Override
    public NastavnikDTO findById(Integer id) {
        NastavnikEntity nastavnik = nastavnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik sa ID-om " + id + " nije pronađen."));
        return nastavnikMapper.toDto(nastavnik);
    }

    @Override
    public NastavnikDTO save(NastavnikDTO nastavnikDTO) {
    	log.info("Čuvanje novog nastavnika");
    	NastavnikEntity nastavnik = nastavnikMapper.toEntity(nastavnikDTO);
        nastavnik = nastavnikRepository.save(nastavnik);
        return nastavnikMapper.toDto(nastavnik);
    }

    @Override
    public NastavnikDTO update(Integer id, NastavnikDTO nastavnikDTO) {
    	log.info("Brisanje nastavnika sa ID: {}", id);
    	NastavnikEntity nastavnikEntity = nastavnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronadjen"));
        nastavnikMapper.updateNastavnikEntityFromDto(nastavnikDTO, nastavnikEntity);
        nastavnikEntity = nastavnikRepository.save(nastavnikEntity);
        return nastavnikMapper.toDto(nastavnikEntity);
    }

    @Override
    public void deleteById(Integer id) {
        nastavnikRepository.deleteById(id);
    }

    @Override
    @Transactional
    public NastavnikDTO pronadjiNastavnikaPoKorisnickomImenu(String korisnickoIme) {
    	log.info("Dohvatanje nastavnika po korisničkom imenu: {}", korisnickoIme);
    	NastavnikEntity nastavnik =nastavnikRepository.findByKorisnikNastavnikKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik nije pronađen za korisnika: " + korisnickoIme));
    	return nastavnikMapper.toDto(nastavnik);
    }
    
    
}
