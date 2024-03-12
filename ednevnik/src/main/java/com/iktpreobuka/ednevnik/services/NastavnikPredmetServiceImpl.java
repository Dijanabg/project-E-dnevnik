package com.iktpreobuka.ednevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.NastavnikPredmetEntity;
import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
import com.iktpreobuka.ednevnik.mappers.NastavnikPredmetMapper;
import com.iktpreobuka.ednevnik.repositories.NastavnikPredmetRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;

@Service
public class NastavnikPredmetServiceImpl implements NastavnikPredmetServise{

	@Autowired
    private NastavnikPredmetRepository nastavnikPredmetRepository;

    @Autowired
    private NastavnikRepository nastavnikRepository;

    @Autowired
    private PredmetRepository predmetRepository;
    
    @Autowired
    private NastavnikPredmetMapper nastavnikPredmetMapper;

    //dodaj nastavnika predmetu
    @Override
    public NastavnikPredmetDTO dodeliNastavnikaPredmetu(Integer nastavnikId, Integer predmetId) {
    	NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId)
                .orElseThrow(() -> new RuntimeException("Nastavnik not found."));
        PredmetEntity predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet not found."));

        NastavnikPredmetEntity nastavnikPredmet = new NastavnikPredmetEntity();
        nastavnikPredmet.setNastavnik(nastavnik);
        nastavnikPredmet.setPredmet(predmet);

        NastavnikPredmetEntity savedNastavnikPredmet = nastavnikPredmetRepository.save(nastavnikPredmet);
        return nastavnikPredmetMapper.toDto(savedNastavnikPredmet);
    }
}
