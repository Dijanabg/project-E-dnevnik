package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Map;

import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;

public interface OcenaService {

	OcenaDTO dodajOcenu(OcenaDTO ocenaDTO);

	OcenaDTO updateOcenu(Integer ocenaId, OcenaDTO ocenaDTO);

	void obrisiOcenu(Integer ocenaId);

	Map<String, List<Integer>> getOcenePoPredmetimaZaUcenika(Integer ucenikId);

}
