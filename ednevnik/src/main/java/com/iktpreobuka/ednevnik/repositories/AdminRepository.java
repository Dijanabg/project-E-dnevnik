package com.iktpreobuka.ednevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ednevnik.entities.AdminEntity;
import com.iktpreobuka.ednevnik.entities.KorisnikEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer>{

	boolean existsByKorisnikAdmin(KorisnikEntity korisnik);

}
