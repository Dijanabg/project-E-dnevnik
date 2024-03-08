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

import com.iktpreobuka.ednevnik.entities.SkolskaGodinaEntity;
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;
import com.iktpreobuka.ednevnik.services.SkolskaGodinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ednevnik/skolskaGodina")
public class SkolskaGodinaController {
	
	@Autowired
	private SkolskaGodinaService skolskaGodinaService;

    @GetMapping("/{id}")
    public ResponseEntity<SkolskaGodinaDTO> getSkolskaGodinaById(@PathVariable Integer id) {
        SkolskaGodinaDTO skolskaGodinaDTO = skolskaGodinaService.findById(id);
        return ResponseEntity.ok(skolskaGodinaDTO);
    }

    @GetMapping
    public ResponseEntity<Iterable<SkolskaGodinaEntity>> getAllSkolskeGodine() {
    	Iterable<SkolskaGodinaEntity> skGod = skolskaGodinaService.findAll();
        return ResponseEntity.ok(skGod);
    }

    @PostMapping
    public ResponseEntity<SkolskaGodinaDTO> createSkolskaGodina(@Valid @RequestBody SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaDTO createdSkolskaGodina = skolskaGodinaService.saveSkolskaGodina(skolskaGodinaDTO);
        return new ResponseEntity<>(createdSkolskaGodina, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkolskaGodinaDTO> updateSkolskaGodina(@PathVariable Integer id, @Valid @RequestBody SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaDTO updatedSkolskaGodina = skolskaGodinaService.updateSkolskaGodina(id, skolskaGodinaDTO);
        return ResponseEntity.ok(updatedSkolskaGodina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkolskaGodina(@PathVariable Integer id) {
        skolskaGodinaService.deleteSkolskaGodina(id);
        return ResponseEntity.noContent().build();
    }    

}
