package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.dto.AdminDTO;
import com.iktpreobuka.ednevnik.exeptions.ResourceNotFoundException;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.AdminService;

@RestController
@RequestMapping("/ednevnik/admini")
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	@JsonView(Views.Admin.class)
    public ResponseEntity<List<AdminDTO>> getAllNastavnici() {
        List<AdminDTO> admin = adminService.findAll();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Integer id) {
    	try {
            AdminDTO adminDTO = adminService.findById(id);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("Greška pri dohvatanju admina: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
    public ResponseEntity<AdminDTO> createAdmin(@Validated @RequestBody AdminDTO adminDTO) {
    	log.info("Kreiranje novog admina");
    	AdminDTO newAdminDTO = adminService.save(adminDTO);
        return new ResponseEntity<>(newAdminDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Integer id,@Validated @RequestBody AdminDTO adminDTO) {
    	log.info("Ažuriranje admina sa ID-om: {}", id);
        try {
            AdminDTO updatedAdminDTO = adminService.update(id, adminDTO);
            return new ResponseEntity<>(updatedAdminDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("Greška pri ažuriranju admina: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
    	log.info("Brisanje admina sa ID-om: {}", id);
        try {
            adminService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Greška pri brisanju admina: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
