package com.iktpreobuka.ednevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;

public interface RazredRepository extends CrudRepository<RazredEntity, Integer>{

	Optional<RazredEntity> findByRazredAndSkolskaGodina(Integer razred, SkolskaGodinaEntity skolskaGodina);
	
}
