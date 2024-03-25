package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.mappers.PredmetMapper;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;

@Service
public class PredmetServiceImpl implements PredmetService{

	@Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private RazredRepository razredRepository;

    @Autowired
    private PredmetMapper predmetMapper;

    @Override
    public List<PredmetDTO> getAllPredmeti() {
        List<PredmetEntity> predmetEntities = (List<PredmetEntity>) predmetRepository.findAll();
        return predmetMapper.toDtoList(predmetEntities);
    }

    @Override
    @Transactional
    public PredmetDTO save(PredmetDTO predmetDTO) {
        PredmetEntity predmetEntity = predmetMapper.toEntity(predmetDTO);
        predmetEntity = predmetRepository.save(predmetEntity);
        return predmetMapper.toDto(predmetEntity);
    }

    @Override
    public PredmetDTO findById(Integer id) {
        PredmetEntity predmetEntity = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet not found"));
        return predmetMapper.toDto(predmetEntity);
    }

    @Override
    @Transactional
    public PredmetDTO update(Integer id, PredmetDTO predmetDTO) {
        PredmetEntity existingPredmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet not found"));
        
        existingPredmet.setNazivPredmeta(predmetDTO.getNazivPredmeta());
        existingPredmet.setCasovaNedeljno(predmetDTO.getCasovaNedeljno());
        
        if (predmetDTO.getRazredId() != null && !existingPredmet.getRazred().getId().equals(predmetDTO.getRazredId())) {
            RazredEntity razred = razredRepository.findById(predmetDTO.getRazredId())
                    .orElseThrow(() -> new RuntimeException("Razred not found"));
            existingPredmet.setRazred(razred);
        }
        existingPredmet = predmetRepository.save(existingPredmet);
        return predmetMapper.toDto(existingPredmet);
    }

    @Override
    public void delete(Integer id) {
        PredmetEntity predmetEntity = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet not found"));
        predmetRepository.delete(predmetEntity);
    }
    
    @Override
    public List<PredmetDTO> findPredmetiByRazredId(Integer razredId) {
        List<PredmetEntity> predmeti = predmetRepository.findByRazredId(razredId);
        return predmetMapper.toDtoList(predmeti);
    }
}
