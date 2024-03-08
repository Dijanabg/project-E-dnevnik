package com.iktpreobuka.ednevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.RazredEntity;
import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.RazredService;

@RestController
@RequestMapping("/ednevnik/razred")
public class RazredController {

	@Autowired
    private RazredService razredService;
	
	@GetMapping
	//@JsonView(Views.Admin.class)
    public ResponseEntity<Iterable<RazredEntity>> getAllRazredi() {
        Iterable<RazredEntity> razredi = razredService.findAll();
        return ResponseEntity.ok(razredi);
    }
	
	// Endpoint za privatne korisnike (nastavnici, roditelji), vraća detaljnije informacije
    @GetMapping("/detalji")
    @JsonView(Views.Private.class)
    public ResponseEntity<Iterable<RazredEntity>> getRazredi() {
    	Iterable<RazredEntity> razredi = razredService.findAll();
        return ResponseEntity.ok(razredi);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<RazredDTO> getRazredById(@PathVariable Integer id) {
        RazredDTO razredDTO = razredService.findById(id);
        return ResponseEntity.ok(razredDTO);
    }
	
	@PostMapping
    public ResponseEntity<RazredEntity> createRazred(@RequestBody RazredDTO razredDTO) {
        RazredEntity razredEntity = razredService.saveRazred(razredDTO);
        return new ResponseEntity<>(razredEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RazredEntity> updateRazred(@PathVariable Integer id, @RequestBody RazredDTO razredDTO) {
        RazredEntity updatedRazred = razredService.updateRazred(id, razredDTO);
        return ResponseEntity.ok(updatedRazred);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRazred(@PathVariable Integer id) {
        razredService.deleteRazred(id);
        return ResponseEntity.noContent().build();
    }
    
}
