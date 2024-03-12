package com.iktpreobuka.ednevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;

public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer>{

	Optional<KorisnikEntity> findByKorisnickoIme(String korisnickoIme);
}
