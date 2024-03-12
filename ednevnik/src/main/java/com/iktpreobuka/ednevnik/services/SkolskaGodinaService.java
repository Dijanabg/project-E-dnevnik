package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;

public interface SkolskaGodinaService {

	List<SkolskaGodinaDTO> findAll();

	SkolskaGodinaDTO save(SkolskaGodinaDTO skolskaGodinaDTO);

	SkolskaGodinaDTO findById(Integer id);

	SkolskaGodinaDTO update(Integer id, SkolskaGodinaDTO skolskaGodinaDTO);

	void deleteById(Integer id);
	
	
}
