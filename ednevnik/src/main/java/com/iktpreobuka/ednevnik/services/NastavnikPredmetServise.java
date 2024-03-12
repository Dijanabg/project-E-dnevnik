package com.iktpreobuka.ednevnik.services;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;

public interface NastavnikPredmetServise {

	NastavnikPredmetDTO dodeliNastavnikaPredmetu(Integer nastavnikId, Integer predmetId);

	
}
