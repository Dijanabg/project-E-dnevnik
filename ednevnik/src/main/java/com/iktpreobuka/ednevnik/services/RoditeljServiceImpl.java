package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.RoditeljEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.RoditeljMapper;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.RoditeljRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;

import jakarta.transaction.Transactional;

@Service
public class RoditeljServiceImpl implements RoditeljService{

	@Autowired
    private RoditeljRepository roditeljRepository;

    @Autowired
    private UcenikRepository ucenikRepository;

    @Autowired
    private RoditeljMapper roditeljMapper;
    
    @Autowired
    private UcenikMapper ucenikMapper;

    @Override
    @Transactional
    public List<RoditeljDTO> findAll() {
        List<RoditeljEntity> roditelji = (List<RoditeljEntity>) roditeljRepository.findAll();
        return roditeljMapper.toDtoList(roditelji);
    }

    @Override
    @Transactional
    public RoditeljDTO save(RoditeljDTO roditeljDTO) {
        RoditeljEntity roditeljEntity = roditeljMapper.toEntity(roditeljDTO);
        roditeljEntity = roditeljRepository.save(roditeljEntity);
        List<UcenikEntity> deca = new ArrayList<>();
        roditeljEntity = roditeljRepository.save(roditeljEntity);
        if(roditeljDTO.getDeteIds() != null && !roditeljDTO.getDeteIds().isEmpty()) {
            for(Integer deteId : roditeljDTO.getDeteIds()) {
                UcenikEntity dete = ucenikRepository.findById(deteId).orElseThrow(() -> new RuntimeException("Dete sa ID-em " + deteId + " nije pronađeno."));
                dete.setRoditelj(roditeljEntity);
                ucenikRepository.save(dete);
            }
        }
        ucenikRepository.saveAll(deca);
        return roditeljMapper.toDto(roditeljEntity);
    }
    
    @Override
    @Transactional
    public RoditeljDTO createRoditeljWithDete(RoditeljDTO roditeljDTO) {
        // Konvertujemo RoditeljDTO u RoditeljEntity
        RoditeljEntity roditeljEntity = roditeljMapper.toEntity(roditeljDTO);

        // Čuvanje roditelja u bazi
        roditeljEntity = roditeljRepository.save(roditeljEntity);

        // Dodajemo roditelja deci (Ucenici) na osnovu deteIds i ažuriramo ih
        for (Integer deteId : roditeljDTO.getDeteIds()) {
            UcenikEntity dete = ucenikRepository.findById(deteId)
                    .orElseThrow(() -> new RuntimeException("Dete not found!")); // Promenite RuntimeException u odgovarajući exception
            dete.setRoditelj(roditeljEntity);
            ucenikRepository.save(dete); // Ovo će ažurirati roditeljId u Ucenik entitetima
        }

        return roditeljMapper.toDto(roditeljEntity);
    }
    @Override
    @Transactional
    public RoditeljDTO findById(Integer id) {
        RoditeljEntity roditelj = roditeljRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Roditelj sa ID-om " + id + " nije pronađen."));
        return roditeljMapper.toDto(roditelj);
    }

    @Override
    @Transactional
    public RoditeljDTO update(Integer id, RoditeljDTO roditeljDTO) {
        RoditeljEntity roditeljEntity = roditeljRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Roditelj sa ID-om " + id + " nije pronađen."));
        roditeljMapper.updateRoditeljEntityFromDto(roditeljDTO, roditeljEntity);
        roditeljEntity = roditeljRepository.save(roditeljEntity);
        return roditeljMapper.toDto(roditeljEntity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        roditeljRepository.deleteById(id);
    }
	
    @Override
    @Transactional
    public RoditeljDTO addDeteToRoditelj(Integer roditeljId, Integer deteId) {
        RoditeljEntity roditelj = roditeljRepository.findById(roditeljId)
                .orElseThrow(() -> new ResourceNotFoundException("Roditelj sa ID-om " + roditeljId + " nije pronađen."));
        UcenikEntity dete = ucenikRepository.findById(deteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dete sa ID-om " + deteId + " nije pronađeno."));
        
        roditelj.getDete().add(dete);
        dete.setRoditelj(roditelj);
        roditeljRepository.save(roditelj);
        
        return roditeljMapper.toDto(roditelj);
    }

    @Override
    @Transactional
    public void removeDeteFromRoditelj(Integer roditeljId, Integer deteId) {
        RoditeljEntity roditelj = roditeljRepository.findById(roditeljId)
                .orElseThrow(() -> new ResourceNotFoundException("Roditelj sa ID-om " + roditeljId + " nije pronađen."));
        UcenikEntity dete = ucenikRepository.findById(deteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dete sa ID-om " + deteId + " nije pronađeno."));
        
        roditelj.getDete().remove(dete);
        dete.setRoditelj(null);
        roditeljRepository.save(roditelj);
    }
    
    //prikazi decu
    @Override
    @Transactional
    public List<UcenikDTO> findDecaByRoditeljId(Integer roditeljId) {
        List<UcenikEntity> deca = ucenikRepository.findByRoditeljId(roditeljId);
        return ucenikMapper.toDtoList(deca);
    }
    
}
