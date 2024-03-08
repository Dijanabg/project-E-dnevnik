package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.PredmetEntity;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.entities.enums.EPolugodisteEntity;
import com.iktpreobuka.ednevnik.mappers.PredmetMapper;
import com.iktpreobuka.ednevnik.repositories.PredmetRepository;

@Service
public class PredmetServiceImpl implements PredmetService{

	@Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private PredmetMapper predmetMapper;
    
	@Override
	@Transactional
	public PredmetDTO savePredmet(PredmetDTO predmetDTO) {
		PredmetEntity predmetEntity = predmetMapper.toEntity(predmetDTO);
        predmetEntity = predmetRepository.save(predmetEntity);
        return predmetMapper.toDto(predmetEntity);
	}

	@Override
	public List<PredmetDTO> findAll() {
		List<PredmetEntity> predmeti = (List<PredmetEntity>) predmetRepository.findAll();
		return predmetMapper.toDtoList(predmeti);
	}

	@Override
	public PredmetDTO findById(Integer id) {
		PredmetEntity predmetEntity = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronadjen!")); 
        return predmetMapper.toDto(predmetEntity);
	}

	@Override
	@Transactional
	public PredmetDTO updatePredmet(Integer id, PredmetDTO predmetDTO) {
		PredmetEntity predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet not found!")); // Consider a more specific exception
        
        // azuriranje
        predmet.setNazivPredmeta(predmetDTO.getNazivPredmeta());
        predmet.setCasovaNedeljno(predmetDTO.getCasovaNedeljno());
        // da li treba polugodiste?
        
        if (predmetDTO.getEpolugodiste() != null && !predmetDTO.getEpolugodiste().isEmpty()) {
            predmet.setEpolugodiste(EPolugodisteEntity.valueOf(predmetDTO.getEpolugodiste()));
        }
        
        predmet = predmetRepository.save(predmet);
        return predmetMapper.toDto(predmet);
	}

	@Override
	public void delete(Integer id) {
		predmetRepository.deleteById(id);
		
	}

}
