package com.iktpreobuka.ednevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.NastavnikPredmetEntity;

public interface NastavnikPredmetRepository extends CrudRepository<NastavnikPredmetEntity, Integer>{
	boolean existsByNastavnikIdAndPredmetId(Integer nastavnikId, Integer predmetId);

	Optional<NastavnikPredmetEntity> findByNastavnikIdAndPredmetId(Integer nastavnikId, Integer predmetId);
}
