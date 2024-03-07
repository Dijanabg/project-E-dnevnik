package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;

public interface UcenikService {
	UcenikDTO saveUcenik(UcenikDTO ucenikDTO);
	
    List<UcenikDTO> findAll();
    
    UcenikDTO findById(Integer id);
    
    UcenikDTO updateUcenik(Integer ucenikId, UcenikDTO ucenikDTO);
    
    void delete(Integer id);
    
    List<UcenikDTO> findByImeAndPrezime(String ime, String prezime);

	void deleteUcenikAndKorisnik(Integer id);
    
}
