package com.iktpreobuka.ednevnik.security;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.RoleDTO;

public interface RoleService {
	
	RoleDTO saveRole(RoleDTO roleDTO);
	
    List<RoleDTO> findAll();
    
    RoleDTO findById(Integer id);
    
    RoleDTO updateRole(Integer id, RoleDTO roleDTO);
    
    void delete(Integer id);
}
