package com.iktpreobuka.ednevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.RazredMapper;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;

@Service
public class RazredServiceImpl implements RazredService{
	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private RazredMapper razredMapper;
	
	@Override
    public RazredDTO findById(Integer id) {
		RazredEntity razredEntity = razredRepository.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Razred not found with id: " + id));
		    return razredMapper.toDto(razredEntity);
    }

    @Override
    public Iterable<RazredEntity> findAll() {
        return razredRepository.findAll();
    }

	@Override
	public RazredEntity saveRazred(RazredDTO razredDTO) {
		RazredEntity razredEntity = razredMapper.toEntity(razredDTO);
        return razredRepository.save(razredEntity);
	}

	@Override
	public RazredEntity updateRazred(Integer id, RazredDTO razredDTO) {
		RazredEntity existingRazred = razredRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Razred not found with id: " + id));
        existingRazred.setRazred(razredDTO.getRazred());
        return razredRepository.save(existingRazred);
	}

	@Override
	public void deleteRazred(Integer id) {
		 RazredEntity razred = razredRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Razred not found with id: " + id));
	        razredRepository.delete(razred);
	}
}
