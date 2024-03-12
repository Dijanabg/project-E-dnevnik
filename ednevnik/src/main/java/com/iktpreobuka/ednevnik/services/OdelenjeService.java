package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;

public interface OdelenjeService {

	OdelenjeDTO createOdelenje(OdelenjeDTO odelenjeDTO);

	OdelenjeDTO updateOdelenje(Integer id, OdelenjeDTO odelenjeDTO);
	
	OdelenjeDTO findOdelenjeById(Integer id);

	void deleteOdelenje(Integer id);

	List<OdelenjeDTO> findAllOdelenja();

	void setRazredniStaresina(Integer odelenjeId, Integer nastavnikId);

	List<UcenikDTO> findAllUceniciInOdelenje(Integer odelenjeId);

	NastavnikDTO getRazredniStaresinaOdOdelenja(Integer odelenjeId);

	UcenikDTO dodeliUcenikaOdelenju(Integer ucenikId, Integer odelenjeId);

	UcenikDTO ukloniUcenikaIzOdelenja(Integer ucenikId);

	
}
