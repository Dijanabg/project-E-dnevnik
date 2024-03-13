package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;
import com.iktpreobuka.ednevnik.services.AdminService;

@RestController
@RequestMapping("/ednevnik/admini")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping
    public ResponseEntity<List<AdminDTO>> getAllNastavnici() {
        List<AdminDTO> admin = adminService.findAll();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Integer id) {
        AdminDTO adminDTO = adminService.findById(id);
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminDTO> createAdmin(@Validated @RequestBody AdminDTO adminDTO) {
        AdminDTO newAdminDTO = adminService.save(adminDTO);
        return new ResponseEntity<>(newAdminDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Integer id,@Validated @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdminDTO = adminService.update(id, adminDTO);
        return new ResponseEntity<>(updatedAdminDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}