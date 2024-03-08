package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;

public interface OdelenjeService {
	OdelenjeDTO saveOdelenje(OdelenjeDTO odelenjeDTO);
    
	List<OdelenjeDTO> findAll();
	
    
	OdelenjeDTO updateOdelenje(Integer id, OdelenjeDTO odelenjeDTO);
    
	void deleteOdelenje(Integer id);
}
