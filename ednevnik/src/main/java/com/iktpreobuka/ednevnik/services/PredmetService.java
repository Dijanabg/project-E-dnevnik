package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;

public interface PredmetService {

	List<PredmetDTO> getAllPredmeti();
	
	PredmetDTO save(PredmetDTO predmetDTO);

	PredmetDTO findById(Integer id);

	PredmetDTO update(Integer id, PredmetDTO predmetDTO);

	void delete(Integer id);

	List<PredmetDTO> findPredmetiByRazredId(Integer razredId);

	
	
}
