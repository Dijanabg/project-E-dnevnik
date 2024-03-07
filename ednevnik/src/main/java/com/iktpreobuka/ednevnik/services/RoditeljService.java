package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;

public interface RoditeljService {
	
	RoditeljDTO saveRoditelj(RoditeljDTO roditeljDTO);
	
    List<RoditeljDTO> findAll();
    
    RoditeljDTO findById(Integer id);
    
    RoditeljDTO updateNastavnik(Integer roditeljId, RoditeljDTO roditeljDTO);
    
    void delete(Integer id);
    
    List<RoditeljDTO> findByImeAndPrezime(String ime, String prezime);
}
