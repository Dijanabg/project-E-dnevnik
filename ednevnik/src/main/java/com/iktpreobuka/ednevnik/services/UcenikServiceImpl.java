package com.iktpreobuka.ednevnik.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.UcenikEntity;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.UcenikMapper;
import com.iktpreobuka.ednevnik.repositories.AdminRepository;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;
import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;
import com.iktpreobuka.ednevnik.repositories.RoditeljRepository;
import com.iktpreobuka.ednevnik.repositories.UcenikRepository;
@Service
public class UcenikServiceImpl implements UcenikService{

	@Autowired
    private UcenikRepository ucenikRepository;

    @Autowired
    private UcenikMapper ucenikMapper;
    
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private RoditeljRepository roditeljRepository;
    @Override
    @Transactional
    public UcenikDTO saveUcenik(UcenikDTO ucenikDTO) {
        KorisnikEntity korisnik = korisnikRepository.findById(ucenikDTO.getKorisnikId())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa ID " + ucenikDTO.getKorisnikId() + " nije pronađen."));
     // Proverava da li korisnik već postoji u nekoj drugoj ulozi
        boolean existsInOtherRole = checkIfKorisnikExistsInOtherRole(korisnik);
        if (existsInOtherRole) {
            throw new IllegalStateException("Korisnik sa ID " + ucenikDTO.getKorisnikId() + " već postoji u drugoj ulozi.");
        }
        UcenikEntity ucenik = ucenikMapper.toEntity(ucenikDTO);
        ucenik.setKorisnikUcenik(korisnik);
        
        ucenik = ucenikRepository.save(ucenik);
        return ucenikMapper.toDto(ucenik);
    }
    
    private boolean checkIfKorisnikExistsInOtherRole(KorisnikEntity korisnik) {
        
        boolean existsAsParent = roditeljRepository.existsByKorisnikRoditelj(korisnik);
        boolean existsAsTeacher = nastavnikRepository.existsByKorisnikNastavnik(korisnik);
        boolean existsAsAdmin = adminRepository.existsByKorisnikAdmin(korisnik);

        return existsAsParent || existsAsTeacher || existsAsAdmin;
    }
    
    @Override
    @Transactional
    public List<UcenikDTO> findAllUcenici() {
        List<UcenikEntity> ucenici = (List<UcenikEntity>) ucenikRepository.findAll();
        return ucenikMapper.toDtoList(ucenici);
    }

    @Override
    @Transactional
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
    
    @Override
    @Transactional
    public UcenikDTO dajInformacijeORazreduZaUcenika(Integer ucenikId) {
        UcenikEntity ucenik = ucenikRepository.findById(ucenikId)
                .orElseThrow(() -> new ResourceNotFoundException("Učenik nije pronađen."));
        
        if (ucenik.getOdelenje() == null) {
            throw new IllegalStateException("Učenik nije dodeljen ni jednom odelenju.");
        }
        
        OdelenjeEntity odelenje = ucenik.getOdelenje();
        RazredEntity razred = odelenje.getRazred();

        UcenikDTO ucenikDTO = new UcenikDTO();
        ucenikDTO.setId(ucenik.getId());
        ucenikDTO.setIme(ucenik.getIme());
        ucenikDTO.setPrezime(ucenik.getPrezime());
        ucenikDTO.setEmail(ucenik.getEmail());
        ucenikDTO.setOdelenje(odelenje.getOdelenje()); // Pretpostavka je da odelenje ima polje 'oznaka'
        ucenikDTO.setRazred(razred.getRazred()); // 'razred' u RazredEntity je tipa Integer

        return ucenikDTO;
    }
}
