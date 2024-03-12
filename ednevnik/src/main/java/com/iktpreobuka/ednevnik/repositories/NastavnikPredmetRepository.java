package com.iktpreobuka.ednevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.NastavnikPredmetEntity;

public interface NastavnikPredmetRepository extends CrudRepository<NastavnikPredmetEntity, Integer>{
	boolean existsByNastavnikIdAndPredmetId(Integer nastavnikId, Integer predmetId);
}
