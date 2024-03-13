package com.iktpreobuka.ednevnik.services;

import java.util.Map;

import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.entities.dto.ZakljucnaOcenaDTO;

public interface OcenaService {

	OcenaDTO dodajOcenu(OcenaDTO ocenaDTO);

	OcenaDTO updateOcenu(Integer ocenaId, OcenaDTO ocenaDTO);

	void obrisiOcenu(Integer ocenaId);

	Map<String, Object> getOcenePoPredmetimaZaUcenika(Integer ucenikId);

	ZakljucnaOcenaDTO dajZakljucnuOcenu(Integer ucenikId, Integer predmetId, Integer zakljucnaOcena);

	Double izracunajProsekZakljucnihOcenaZaUcenika(Integer ucenikId);

	

}
