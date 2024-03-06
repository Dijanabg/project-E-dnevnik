package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;

public interface NastavnikService {
	NastavnikDTO saveNastavnik(NastavnikDTO nastavnikDTO);
	
    List<NastavnikDTO> findAll();
    
    NastavnikDTO findById(Integer id);
    
    NastavnikDTO updateNastavnik(Integer nastavnikId, NastavnikDTO nastavnikDTO);
    
    void delete(Integer id);
    
    List<NastavnikDTO> findByImeAndPrezime(String ime, String prezime);
}
