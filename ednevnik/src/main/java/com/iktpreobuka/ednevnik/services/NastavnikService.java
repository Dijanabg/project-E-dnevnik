package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;

public interface NastavnikService {

	List<NastavnikDTO> findAll();

	NastavnikDTO findById(Integer id);

	NastavnikDTO save(NastavnikDTO nastavnikDTO);

	NastavnikDTO update(Integer id, NastavnikDTO nastavnikDTO);

	void deleteById(Integer id);
	
}
