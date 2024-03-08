package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;

public interface PredmetService {
	PredmetDTO savePredmet(PredmetDTO predmetDTO);
    List<PredmetDTO> findAll();
    PredmetDTO findById(Integer id);
    PredmetDTO updatePredmet(Integer id, PredmetDTO predmetDTO);
    void delete(Integer id);
}
