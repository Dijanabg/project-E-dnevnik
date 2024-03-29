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
import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.RoditeljService;

@RestController
@RequestMapping("/ednevnik/roditelji")
public class RoditeljController {
	
	@Autowired
	private RoditeljService roditeljService;


	@GetMapping
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	@JsonView(Views.Admin.class)
    public ResponseEntity<List<RoditeljDTO>> getAllRoditelji() {
        List<RoditeljDTO> roditelji = roditeljService.findAll();
        return new ResponseEntity<>(roditelji, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<RoditeljDTO> createRoditelj(@Validated @RequestBody RoditeljDTO roditeljDTO) {
        RoditeljDTO createdRoditelj = roditeljService.save(roditeljDTO);
        return new ResponseEntity<>(createdRoditelj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<RoditeljDTO> updateRoditelj(@PathVariable Integer id,@Validated @RequestBody RoditeljDTO roditeljDTO) {
        RoditeljDTO updatedRoditelj = roditeljService.update(id, roditeljDTO);
        return new ResponseEntity<>(updatedRoditelj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> deleteRoditelj(@PathVariable Integer id) {
        roditeljService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/{roditeljId}/dodaj-dete/{deteId}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<RoditeljDTO> dodajDeteRoditelju(@PathVariable Integer roditeljId, @PathVariable Integer deteId) {
        RoditeljDTO roditeljDTO = roditeljService.addDeteToRoditelj(roditeljId, deteId);
        return ResponseEntity.ok(roditeljDTO);
    }

    @DeleteMapping("/{roditeljId}/ukloni-dete/{deteId}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> ukloniDeteIzRoditelja(@PathVariable Integer roditeljId, @PathVariable Integer deteId) {
        roditeljService.removeDeteFromRoditelj(roditeljId, deteId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{roditeljId}/ucenici")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    @JsonView(Views.Private.class)
    public ResponseEntity<RoditeljDTO> dohvatiDecuZaRoditelja(@PathVariable Integer roditeljId) {
        RoditeljDTO roditelj = roditeljService.findDecaByRoditeljId(roditeljId);
        return ResponseEntity.ok(roditelj);
    }
}
