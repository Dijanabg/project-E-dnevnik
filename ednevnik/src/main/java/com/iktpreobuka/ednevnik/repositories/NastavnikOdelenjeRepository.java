package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikOdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;

public interface NastavnikOdelenjeRepository extends CrudRepository<NastavnikOdelenjeEntity, Integer>{

	boolean existsByPredavacAndOdelenjeAndPredmet(NastavnikEntity predavac, OdelenjeEntity odelenje, PredmetEntity predmet);
	List<NastavnikOdelenjeEntity> findAllByOdelenjeId(Integer odeljenjeId);
}
