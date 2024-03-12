package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;

public interface KorisnikService {

	List<KorisnikDTO> findAll();

	KorisnikDTO save(KorisnikDTO korisnikDto);

	KorisnikDTO findById(Integer id);

	KorisnikDTO update(Integer id, KorisnikDTO korisnikDTO);

	void deleteById(Integer id);

	KorisnikDTO findByKorisickoIme(String korisnickoIme);
}
