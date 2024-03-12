package com.iktpreobuka.ednevnik.mappers;

import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
@Component
public class NastavnikPredmetMapper {
	public NastavnikPredmetDTO toDto(NastavnikPredmetEntity nastavnikPredmet) {
		if (nastavnikPredmet == null) {
            return null;
        }
        NastavnikPredmetDTO dto = new NastavnikPredmetDTO();
        dto.setId(nastavnikPredmet.getId());
        dto.setNastavnikId(nastavnikPredmet.getNastavnik().getId());
        dto.setNastavnikIme(nastavnikPredmet.getNastavnik().getIme()); // Pretpostavka da postoji polje "ime" u entitetu Nastavnik
        dto.setPredmetId(nastavnikPredmet.getPredmet().getId());
        dto.setPredmetNaziv(nastavnikPredmet.getPredmet().getNazivPredmeta()); // Pretpostavka da postoji polje "nazivPredmeta" u entitetu Predmet
        
        return dto;
    }
}
