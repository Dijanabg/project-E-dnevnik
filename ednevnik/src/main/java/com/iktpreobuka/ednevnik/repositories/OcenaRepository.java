package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.OcenaEntity;

public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer>{

	List<OcenaEntity> findAllByUcenikId(Integer ucenikId);

}
