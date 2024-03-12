package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;

public interface RazredService {

	List<RazredDTO> findAll();

	RazredDTO save(RazredDTO razredDTO);

	RazredDTO findById(Integer id);

	RazredDTO update(Integer id, RazredDTO razredDTO);

	void deleteById(Integer id);
	
}
