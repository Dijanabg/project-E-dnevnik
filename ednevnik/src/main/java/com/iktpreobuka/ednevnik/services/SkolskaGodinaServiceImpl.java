package com.iktpreobuka.ednevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.SkolskaGodinaMapper;
import com.iktpreobuka.ednevnik.repositories.SkolskaGodinaRepository;

@Service
public class SkolskaGodinaServiceImpl implements SkolskaGodinaService{
	@Autowired
	private SkolskaGodinaRepository skolskaGodinaRepository;
	
	@Autowired
    private SkolskaGodinaMapper skolskaGodinaMapper;

	@Override
	public SkolskaGodinaDTO findById(Integer id) {
		SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skolska godina not found with id: " + id));
	        return skolskaGodinaMapper.toDto(skolskaGodina);
	}

	@Override
	public Iterable<SkolskaGodinaEntity> findAll() {
		return skolskaGodinaRepository.findAll();
	}

	@Override
	public SkolskaGodinaDTO saveSkolskaGodina(SkolskaGodinaDTO skolskaGodinaDTO) {
		SkolskaGodinaEntity skolskaGodina = skolskaGodinaMapper.toEntity(skolskaGodinaDTO);
        skolskaGodina = skolskaGodinaRepository.save(skolskaGodina);
        return skolskaGodinaMapper.toDto(skolskaGodina);
	}

	@Override
	public SkolskaGodinaDTO updateSkolskaGodina(Integer id, SkolskaGodinaDTO skolskaGodinaDTO) {
		SkolskaGodinaEntity existingSkolskaGodina = skolskaGodinaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Skolska godina not found with id: " + id));
	        existingSkolskaGodina.setOznaka(skolskaGodinaDTO.getOznaka());
	        existingSkolskaGodina = skolskaGodinaRepository.save(existingSkolskaGodina);
	        return skolskaGodinaMapper.toDto(existingSkolskaGodina);
	}

	@Override
	public void deleteSkolskaGodina(Integer id) {
		if (!skolskaGodinaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nije pronadjena skolska godina sa id: " + id);
        }
        skolskaGodinaRepository.deleteById(id);
	}
	
	
}
