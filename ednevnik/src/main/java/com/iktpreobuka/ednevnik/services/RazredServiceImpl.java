package com.iktpreobuka.ednevnik.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.RazredMapper;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;
import com.iktpreobuka.ednevnik.repositories.SkolskaGodinaRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class RazredServiceImpl implements RazredService{
	@Autowired
    private RazredRepository razredRepository;
    
    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    private RazredMapper razredMapper;

    @Override
    @Transactional
    public List<RazredDTO> findAll() {
        List<RazredEntity> razredi = (List<RazredEntity>) razredRepository.findAll();
        return razredMapper.toDtoList(razredi);
    }


    @Override
    @Transactional
    public RazredDTO save(RazredDTO razredDTO) {
        SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(razredDTO.getSkolskaGodinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Školska godina sa ID-om " + razredDTO.getSkolskaGodinaId() + " nije pronađena."));
        Optional<RazredEntity> postojeciRazred = razredRepository.findByRazredAndSkolskaGodina(razredDTO.getRazred(), skolskaGodina);
        if(postojeciRazred.isPresent()) {
            throw new EntityExistsException("Razred " + razredDTO.getRazred() + " već postoji za školsku godinu sa ID-om " + razredDTO.getSkolskaGodinaId() + ".");
        }
        RazredEntity razredEntity = razredMapper.toEntity(razredDTO);
        razredEntity.setSkolskaGodina(skolskaGodina);
        razredEntity = razredRepository.save(razredEntity);
        return razredMapper.toDto(razredEntity);
    }

    @Override
    public RazredDTO findById(Integer id) {
        RazredEntity razred = razredRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Razred sa ID-om " + id + " nije pronađen."));
        return razredMapper.toDto(razred);
    }
    
    @Override
    public RazredDTO update(Integer id, RazredDTO razredDTO) {
        RazredEntity razredEntity = razredRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Razred sa ID-om " + id + " nije pronađen."));

        SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(razredDTO.getSkolskaGodinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Školska godina sa ID-om " + razredDTO.getSkolskaGodinaId() + " nije pronađena."));

        razredEntity.setRazred(razredDTO.getRazred());
        razredEntity.setSkolskaGodina(skolskaGodina);
        razredRepository.save(razredEntity);
        return razredMapper.toDto(razredEntity);
    }

    @Override
    public void deleteById(Integer id) {
        razredRepository.deleteById(id);
    }
    
    //dodaj predmete razredu
    
    //prikazi sve predmete po razredu
}
