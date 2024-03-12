package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;
import com.iktpreobuka.ednevnik.mappers.SkolskaGodinaMapper;
import com.iktpreobuka.ednevnik.repositories.SkolskaGodinaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SkolskaGodinaServiceImpl implements SkolskaGodinaService{
	@Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    private SkolskaGodinaMapper skolskaGodinaMapper;

    // Prikaz svih školskih godina
    @Override
    public List<SkolskaGodinaDTO> findAll() {
        List<SkolskaGodinaEntity> skolskeGodine = (List<SkolskaGodinaEntity>) skolskaGodinaRepository.findAll();
        List<SkolskaGodinaDTO> skolskaGodinaDTOs = new ArrayList<>();
        for (SkolskaGodinaEntity skolskaGodina : skolskeGodine) {
            skolskaGodinaDTOs.add(skolskaGodinaMapper.toDto(skolskaGodina));
        }
        return skolskaGodinaDTOs;
    }

    // Dodavanje nove školske godine
    @Override
    public SkolskaGodinaDTO save(SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaEntity skolskaGodinaEntity = skolskaGodinaMapper.toEntity(skolskaGodinaDTO);
        skolskaGodinaEntity = skolskaGodinaRepository.save(skolskaGodinaEntity);
        return skolskaGodinaMapper.toDto(skolskaGodinaEntity);
    }
 // Prikaz detalja o određenoj školskoj godini
    @Override
    public SkolskaGodinaDTO findById(Integer id) {
        SkolskaGodinaEntity skolskaGodinaEntity = skolskaGodinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Školska godina sa ID-om " + id + " nije pronađena."));
        return skolskaGodinaMapper.toDto(skolskaGodinaEntity);
    }
    // Ažuriranje postojeće školske godine
    @Override
    public SkolskaGodinaDTO update(Integer id, SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaEntity skolskaGodinaEntity = skolskaGodinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Školska godina sa ID-om " + id + " nije pronađena."));
        skolskaGodinaEntity.setOznaka(skolskaGodinaDTO.getOznaka());
        skolskaGodinaEntity = skolskaGodinaRepository.save(skolskaGodinaEntity);
        return skolskaGodinaMapper.toDto(skolskaGodinaEntity);
    }

    // Brisanje školske godine
    @Override
    public void deleteById(Integer id) {
        skolskaGodinaRepository.deleteById(id);
    }

}
