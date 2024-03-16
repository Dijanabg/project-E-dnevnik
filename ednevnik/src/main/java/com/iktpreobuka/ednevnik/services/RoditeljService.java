package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;

public interface RoditeljService {

	List<RoditeljDTO> findAll();

	RoditeljDTO save(RoditeljDTO roditeljDTO);

	RoditeljDTO findById(Integer id);

	RoditeljDTO update(Integer id, RoditeljDTO roditeljDTO);
	
	void deleteById(Integer id);

	RoditeljDTO addDeteToRoditelj(Integer roditeljId, Integer deteId);

	void removeDeteFromRoditelj(Integer roditeljId, Integer deteId);

	RoditeljDTO createRoditeljWithDete(RoditeljDTO roditeljDTO);

	RoditeljDTO findDecaByRoditeljId(Integer roditeljId);



	
	
	
}
