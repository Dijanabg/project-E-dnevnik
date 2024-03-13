package com.iktpreobuka.ednevnik.services;

import java.util.List;

import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;

public interface AdminService {

	List<AdminDTO> findAll();

	AdminDTO findById(Integer id);

	AdminDTO save(AdminDTO nastavnikDTO);

	AdminDTO update(Integer id, AdminDTO adminDTO);

	void deleteById(Integer id);

}
