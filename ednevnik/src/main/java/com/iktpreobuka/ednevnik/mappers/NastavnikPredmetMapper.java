package com.iktpreobuka.ednevnik.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iktpreobuka.ednevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
@Component
public class NastavnikPredmetMapper {
	
	@Autowired PredmetMapper predmetMapper;
	
	@Autowired RazredMapper razredMapper;
	
	public NastavnikPredmetDTO toDto(NastavnikPredmetEntity nastavnikPredmet) {
		if (nastavnikPredmet == null) {
            return null;
        } 
        NastavnikPredmetDTO dto = new NastavnikPredmetDTO();
        dto.setId(nastavnikPredmet.getId());
        dto.setNastavnikId(nastavnikPredmet.getNastavnik().getId());
        dto.setNastavnikIme(nastavnikPredmet.getNastavnik().getIme());
        dto.setNastavnikPrezime(nastavnikPredmet.getNastavnik().getPrezime());
        dto.setPredmetId(nastavnikPredmet.getPredmet().getId());
        dto.setPredmetNaziv(nastavnikPredmet.getPredmet().getNazivPredmeta()); 
        //dto.setPredmetRazred(Predmet.getPredmet().getRazred());
        if (nastavnikPredmet.getPredmet().getRazred() != null) {
            
            Integer razredNaziv = nastavnikPredmet.getPredmet().getRazred().getRazred();
            dto.setPredmetRazred(razredNaziv);

            
            // dto.setRazred(predmetMapper.toRazredDto(nastavnikPredmet.getPredmet().getRazred()));
        }
        return dto;
    }
}
