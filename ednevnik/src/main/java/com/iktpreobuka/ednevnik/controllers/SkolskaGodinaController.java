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
import com.iktpreobuka.ednevnik.entities.dto.SkolskaGodinaDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.SkolskaGodinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ednevnik/skolskaGodina")
public class SkolskaGodinaController {
	
	@Autowired
    private SkolskaGodinaService skolskaGodinaService;

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<SkolskaGodinaDTO>> getAllSkolskeGodine() {
        List<SkolskaGodinaDTO> skolskeGodine = skolskaGodinaService.findAll();
        return new ResponseEntity<>(skolskeGodine, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Private.class)
    public ResponseEntity<SkolskaGodinaDTO> getSkolskaGodinaById(@PathVariable Integer id) {
        SkolskaGodinaDTO skolskaGodinaDTO = skolskaGodinaService.findById(id);
        return new ResponseEntity<>(skolskaGodinaDTO, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<SkolskaGodinaDTO> addSkolskaGodina(@Validated @RequestBody SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaDTO novaSkolskaGodina = skolskaGodinaService.save(skolskaGodinaDTO);
        return new ResponseEntity<>(novaSkolskaGodina, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<SkolskaGodinaDTO> updateSkolskaGodina(@PathVariable Integer id, @Valid @RequestBody SkolskaGodinaDTO skolskaGodinaDTO) {
        SkolskaGodinaDTO azuriranaSkolskaGodina = skolskaGodinaService.update(id, skolskaGodinaDTO);
        return new ResponseEntity<>(azuriranaSkolskaGodina, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> deleteSkolskaGodina(@PathVariable Integer id) {
        skolskaGodinaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
