package com.iktpreobuka.ednevnik.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ednevnik.entities.RoleEntity;
import com.iktpreobuka.ednevnik.entities.dto.RoleDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.mappers.RoleMapper;
import com.iktpreobuka.ednevnik.repositories.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RoleDTO saveRole(RoleDTO roleDTO) {
		RoleEntity entity = roleMapper.toEntity(roleDTO);
        entity = roleRepository.save(entity);
        return roleMapper.toDto(entity);
	}

	@Override
	public List<RoleDTO> findAll() {
		Iterable<RoleEntity> entities = roleRepository.findAll();
        List<RoleDTO> dtos = new ArrayList<>();
        for(RoleEntity entity : entities) {
            dtos.add(roleMapper.toDto(entity));
        }
        return dtos;
	}

	@Override
	public RoleDTO findById(Integer id) {
		RoleEntity entity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        return roleMapper.toDto(entity);
	}

	@Override
    public RoleDTO updateRole(Integer id, RoleDTO roleDTO) {
		RoleEntity existingRole = roleRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));

	    // Provera da li postoji rola sa novim imenom, ali sa različitim ID-jem
		RoleEntity roleWithSameName = roleRepository.findByName(roleDTO.getName());
	    if (roleWithSameName != null && !roleWithSameName.getId().equals(existingRole.getId())) {
	        throw new DataIntegrityViolationException("Rola sa ovim imenom vec postoji!");
	    }
	    existingRole.setName(roleDTO.getName());
	    existingRole = roleRepository.save(existingRole);
	    return roleMapper.toDto(existingRole);
    }

	@Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
