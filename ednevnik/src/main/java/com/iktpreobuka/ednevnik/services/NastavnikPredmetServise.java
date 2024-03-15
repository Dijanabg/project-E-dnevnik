package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;

public interface NastavnikPredmetServise {

	NastavnikPredmetDTO dodeliNastavnikaPredmetu(Integer nastavnikId, Integer predmetId);

	List<NastavnikPredmetDTO> getNastavniciIPredmetiKojePredaju();

	boolean ukloniDodeluNastavnikaPredmetu(Integer nastavnikId, Integer predmetId);

	
}
