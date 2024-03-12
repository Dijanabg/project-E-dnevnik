package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.NastavnikMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;


@Service
public class NastavnikServiceImpl implements NastavnikService{
	
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
        NastavnikEntity nastavnik = nastavnikMapper.toEntity(nastavnikDTO);
        nastavnik = nastavnikRepository.save(nastavnik);
        return nastavnikMapper.toDto(nastavnik);
    }

    @Override
    public NastavnikDTO update(Integer id, NastavnikDTO nastavnikDTO) {
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

    
    
    // uklanjanje dodeljenog nastavnika odelenju u odelenje
    // pregled nastavnika sa listom predmeta koje predae i u kojim odelenjima
}
