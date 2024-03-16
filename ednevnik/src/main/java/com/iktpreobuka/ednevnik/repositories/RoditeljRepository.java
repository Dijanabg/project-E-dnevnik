package com.iktpreobuka.ednevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.RoditeljEntity;

public interface RoditeljRepository extends CrudRepository<RoditeljEntity, Integer>{
	List<RoditeljEntity> findByImeAndPrezime(String ime, String prezime);

}
