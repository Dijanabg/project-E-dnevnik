package com.iktpreobuka.ednevnik.services;

import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;

public interface SkolskaGodinaService {
	
	SkolskaGodinaDTO findById(Integer id);
	
    Iterable<SkolskaGodinaEntity> findAll();
    
    SkolskaGodinaDTO saveSkolskaGodina(SkolskaGodinaDTO skolskaGodinaDTO);
    
    SkolskaGodinaDTO updateSkolskaGodina(Integer id, SkolskaGodinaDTO skolskaGodinaDTO);
    
    void deleteSkolskaGodina(Integer id);
}
