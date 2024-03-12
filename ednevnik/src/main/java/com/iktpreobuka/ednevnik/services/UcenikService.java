package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;

public interface UcenikService {

	UcenikDTO saveUcenik(UcenikDTO ucenikDTO);

	List<UcenikDTO> findAllUcenici();

	UcenikDTO findUcenikById(Integer id);

	UcenikDTO updateUcenik(Integer id, UcenikDTO ucenikDTO);

	void deleteUcenik(Integer id);

	List<UcenikDTO> nadjiDecuNegogRoditelja(Integer roditeljId);
	
    
}
