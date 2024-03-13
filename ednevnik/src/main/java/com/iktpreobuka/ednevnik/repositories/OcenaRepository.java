package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.OcenaEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;

public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer>{

	List<OcenaEntity> findAllByUcenikId(Integer ucenikId);

	List<OcenaEntity> findAllByUcenikAndPredmet(UcenikEntity ucenik, PredmetEntity predmet);

}
