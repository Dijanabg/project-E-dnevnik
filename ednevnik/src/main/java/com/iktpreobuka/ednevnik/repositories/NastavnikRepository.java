package com.iktpreobuka.ednevnik.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikEntity;

public interface NastavnikRepository extends CrudRepository<NastavnikEntity, Integer>{
	List<NastavnikEntity> findByImeAndPrezime(String ime, String prezime);

	Optional<NastavnikEntity> findByKorisnikNastavnikKorisnickoIme(String korisnickoIme);

	boolean existsByKorisnikNastavnik(KorisnikEntity korisnik);
}
