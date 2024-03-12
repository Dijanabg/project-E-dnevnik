package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.PredmetEntity;

public interface PredmetRepository extends CrudRepository<PredmetEntity, Integer>{

	List<PredmetEntity> findByRazredId(Integer razredId);
}
