package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.UcenikEntity;

public interface UcenikRepository extends CrudRepository<UcenikEntity, Integer>{
	List<UcenikEntity> findByImeAndPrezime(String ime, String prezime);
}
