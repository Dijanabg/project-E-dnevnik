package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.OdelenjeEntity;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.mappers.OdelenjeMapper;
import com.iktpreobuka.ednevnik.repositories.OdelenjeRepository;
import com.iktpreobuka.ednevnik.repositories.RazredRepository;
import com.iktpreobuka.ednevnik.repositories.SkolskaGodinaRepository;

@Service
public class OdelenjeServiceImpl implements OdelenjeService{

	@Autowired
    private OdelenjeRepository odelenjeRepository;

    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    private RazredRepository razredRepository;

    @Autowired
    private OdelenjeMapper odelenjeMapper;
    
	@Override
    public List<OdelenjeDTO> findAll() {
        List<OdelenjeEntity> odelenja = (List<OdelenjeEntity>) odelenjeRepository.findAll();
        return odelenjeMapper.toDtoList(odelenja);
    }
	
	@Override
    public OdelenjeDTO saveOdelenje(OdelenjeDTO odelenjeDTO) {
        OdelenjeEntity odelenje = odelenjeMapper.toEntity(odelenjeDTO);

        SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(odelenjeDTO.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Školska godina sa ID " + odelenjeDTO.getSkolskaGodinaId() + " nije pronađena."));

        RazredEntity razred = razredRepository.findById(odelenjeDTO.getRazred())
                .orElseThrow(() -> new RuntimeException("Razred sa ID " + odelenjeDTO.getRazred() + " nije pronađen."));

        odelenje.setSkolskaGodina(skolskaGodina);
        odelenje.setRazred(razred);
        odelenje = odelenjeRepository.save(odelenje);
        
        return odelenjeMapper.toDto(odelenje);
    }
	
	@Override
    public void deleteOdelenje(Integer id) {
        odelenjeRepository.deleteById(id);
    }

	@Override
	public OdelenjeDTO updateOdelenje(Integer id, OdelenjeDTO odelenjeDTO) {
		OdelenjeEntity odelenje = odelenjeRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Odelenje sa ID " + id + " nije pronađeno."));

	    odelenje.setAktivno(odelenjeDTO.isAktivno());
	    odelenje.setOdelenje(odelenjeDTO.getOdelenje());

	    SkolskaGodinaEntity skolskaGodina = skolskaGodinaRepository.findById(odelenjeDTO.getSkolskaGodinaId())
	            .orElseThrow(() -> new RuntimeException("Školska godina sa ID " + odelenjeDTO.getSkolskaGodinaId() + " nije pronađena."));
	    odelenje.setSkolskaGodina(skolskaGodina);

	    RazredEntity razred = razredRepository.findById(odelenjeDTO.getRazred())
	            .orElseThrow(() -> new RuntimeException("Razred sa ID " + odelenjeDTO.getRazred() + " nije pronađen."));
	    odelenje.setRazred(razred);

	    odelenje = odelenjeRepository.save(odelenje);
	    return odelenjeMapper.toDto(odelenje);
	}
}
