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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.dto.RoleDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.RoleService;

@RestController
@RequestMapping("/ednevnik/roles")
public class RoleController {

	@Autowired 
	private RoleService roleService;
	
	@GetMapping
	@Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        List<RoleDTO> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
	
	@PostMapping
	@Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
    public ResponseEntity<RoleDTO> addRole(@Validated @RequestBody RoleDTO roleDTO) {
        RoleDTO newRole = roleService.saveRole(roleDTO);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Integer id,@Validated @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        if(updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
    	roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
