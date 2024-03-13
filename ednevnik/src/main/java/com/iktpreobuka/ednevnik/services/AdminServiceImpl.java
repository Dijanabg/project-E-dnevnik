package com.iktpreobuka.ednevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.AdminEntity;
import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.AdminMapper;
import com.iktpreobuka.ednevnik.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<AdminDTO> findAll() {
    	List<AdminEntity> admini = (List<AdminEntity>) adminRepository.findAll();
        return adminMapper.toDtoList(admini);
    }

    @Override
    public AdminDTO findById(Integer id) {
    	AdminEntity admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin sa ID-om " + id + " nije pronađen."));
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO save(AdminDTO adminDTO) {
    	AdminEntity admin = adminMapper.toEntity(adminDTO);
    	admin = adminRepository.save(admin);
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO update(Integer id, AdminDTO adminDTO) {
    	AdminEntity adminEntity = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin nije pronadjen"));
    	adminMapper.updateAdminEntityFromDto(adminDTO, adminEntity);
    	adminEntity = adminRepository.save(adminEntity);
        return adminMapper.toDto(adminEntity);
    }

    @Override
    public void deleteById(Integer id) {
    	adminRepository.deleteById(id);
    }
}
