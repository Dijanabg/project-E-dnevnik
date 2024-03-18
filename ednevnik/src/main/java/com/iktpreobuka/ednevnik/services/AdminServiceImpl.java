package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.AdminEntity;
import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.AdminMapper;
import com.iktpreobuka.ednevnik.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<AdminDTO> findAll() {
    	log.info("Dohvatanje svih admina");
    	List<AdminEntity> admini = (List<AdminEntity>) adminRepository.findAll();
        return adminMapper.toDtoList(admini);
    }

    @Override
    public AdminDTO findById(Integer id) {
    	log.info("Traženje admina po ID-u: {}", id);
    	AdminEntity admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin sa ID-om " + id + " nije pronađen."));
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO save(AdminDTO adminDTO) {
    	log.info("Čuvanje novog admina: {}", adminDTO);
    	AdminEntity admin = adminMapper.toEntity(adminDTO);
    	admin = adminRepository.save(admin);
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO update(Integer id, AdminDTO adminDTO) {
    	log.info("Ažuriranje admina sa ID-om: {}", id);
    	AdminEntity adminEntity = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin nije pronadjen"));
    	adminMapper.updateAdminEntityFromDto(adminDTO, adminEntity);
    	adminEntity = adminRepository.save(adminEntity);
        return adminMapper.toDto(adminEntity);
    }

    @Override
    public void deleteById(Integer id) {
    	log.info("Brisanje admina sa ID-om: {}", id);
    	adminRepository.deleteById(id);
    }
}
