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

import com.iktpreobuka.ednevnik.entities.dto.RazredDTO;
import com.iktpreobuka.ednevnik.services.RazredService;

@RestController
@RequestMapping("/ednevnik/razred")
public class RazredController {

	@Autowired
    private RazredService razredService;
	
    @GetMapping
    public ResponseEntity<List<RazredDTO>> getAllRazredi() {
        List<RazredDTO> razredi = razredService.findAll();
        return new ResponseEntity<>(razredi, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<RazredDTO> createRazred(@Validated @RequestBody RazredDTO razredDTO) {
        RazredDTO createdRazred = razredService.save(razredDTO);
        return new ResponseEntity<>(createdRazred, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazredDTO> getRazredById(@PathVariable Integer id) {
        RazredDTO razredDTO = razredService.findById(id);
        return new ResponseEntity<>(razredDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<RazredDTO> updateRazred(@PathVariable Integer id, @Validated @RequestBody RazredDTO razredDTO) {
        RazredDTO updatedRazred = razredService.update(id, razredDTO);
        return new ResponseEntity<>(updatedRazred, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteRazred(@PathVariable Integer id) {
        razredService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

