package com.iktpreobuka.ednevnik.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;


public interface UcenikRepository extends CrudRepository<UcenikEntity, Integer>{
	List<UcenikEntity> findByImeAndPrezime(String ime, String prezime);
	
	 List<UcenikEntity> findByRoditeljId(Integer roditeljId);
	 
	 boolean existsByKorisnikUcenik(KorisnikEntity korisnik);

	Optional<UcenikEntity> findById( UcenikDTO ucenikId);
}
