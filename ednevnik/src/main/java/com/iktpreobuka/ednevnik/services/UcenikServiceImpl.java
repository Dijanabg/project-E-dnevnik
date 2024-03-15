package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;
@Service
public class UcenikServiceImpl implements UcenikService{

	@Autowired
    private UcenikRepository ucenikRepository;

    @Autowired
    private UcenikMapper ucenikMapper;
    
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    @Transactional
    public UcenikDTO saveUcenik(UcenikDTO ucenikDTO) {
        UcenikEntity ucenik = ucenikMapper.toEntity(ucenikDTO);
     // postavlja korisnika na osnovu tog Id
        if (ucenikDTO.getKorisnikId() != null) {
            KorisnikEntity korisnik = korisnikRepository.findById(ucenikDTO.getKorisnikId())
                    .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa ID " + ucenikDTO.getKorisnikId() + " nije pronađen."));
            ucenik.setKorisnikUcenik(korisnik);
        }

        ucenik = ucenikRepository.save(ucenik);
        return ucenikMapper.toDto(ucenik);
    }

    @Override
    @Transactional
    public List<UcenikDTO> findAllUcenici() {
        List<UcenikEntity> ucenici = (List<UcenikEntity>) ucenikRepository.findAll();
        return ucenikMapper.toDtoList(ucenici);
    }

    @Override
    public UcenikDTO findUcenikById(Integer id) {
        UcenikEntity ucenik = ucenikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ucenik not found with id " + id));
        return ucenikMapper.toDto(ucenik);
    }

    @Override
    @Transactional
    public UcenikDTO updateUcenik(Integer id, UcenikDTO ucenikDTO) {
        UcenikEntity ucenik = ucenikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ucenik not found with id " + id));
        ucenikMapper.updateUcenikEntityFromDto(ucenikDTO, ucenik);
        ucenik = ucenikRepository.save(ucenik);
        return ucenikMapper.toDto(ucenik);
    }

    @Override
    public void deleteUcenik(Integer id) {
        UcenikEntity ucenik = ucenikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ucenik not found with id " + id));
        ucenikRepository.delete(ucenik);
    }
    @Override
    public List<UcenikDTO> nadjiDecuNegogRoditelja(Integer roditeljId) {
        List<UcenikEntity> ucenici = ucenikRepository.findByRoditeljId(roditeljId);
        List<UcenikDTO> uceniciDTO = new ArrayList<>();
        for (UcenikEntity ucenik : ucenici) {
            UcenikDTO ucenikDTO = ucenikMapper.toDto(ucenik);
            uceniciDTO.add(ucenikDTO);
        }
        return uceniciDTO;
    }
}
