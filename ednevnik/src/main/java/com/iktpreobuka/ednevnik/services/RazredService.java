package com.iktpreobuka.ednevnik.services;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;

public interface RazredService {
	RazredDTO findById(Integer id);
	
	Iterable<RazredEntity> findAll();
	
	RazredEntity saveRazred(RazredDTO razredDTO);

    RazredEntity updateRazred(Integer id, RazredDTO razredDTO);

    void deleteRazred(Integer id);
}
