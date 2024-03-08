package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.services.OdelenjeService;

@RestController
@RequestMapping("/ednevnik/odelenja")
public class OdelenjeController {
	@Autowired
    private OdelenjeService odelenjeService;

    @PostMapping
    public ResponseEntity<OdelenjeDTO> createOdelenje(@Validated @RequestBody OdelenjeDTO odelenjeDTO) {
        return ResponseEntity.ok(odelenjeService.saveOdelenje(odelenjeDTO));
    }

    @GetMapping
    public ResponseEntity<List<OdelenjeDTO>> getAllOdelenja() {
        return ResponseEntity.ok(odelenjeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdelenjeDTO> updateOdelenje(@PathVariable Integer id,@Validated @RequestBody OdelenjeDTO odelenjeDTO) {
        return ResponseEntity.ok(odelenjeService.updateOdelenje(id, odelenjeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOdelenje(@PathVariable Integer id) {
        odelenjeService.deleteOdelenje(id);
        return ResponseEntity.ok().build();
    }
}
