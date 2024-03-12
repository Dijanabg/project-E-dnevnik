package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

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

import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.services.UcenikService;


@RestController
@RequestMapping("/ednevnik/ucenici")
public class UcenikController {

	@Autowired
    private UcenikService ucenikService;

    @PostMapping
    public ResponseEntity<UcenikDTO> createUcenik(@RequestBody UcenikDTO ucenikDTO) {
        return new ResponseEntity<>(ucenikService.saveUcenik(ucenikDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UcenikDTO>> getAllUcenici() {
        return ResponseEntity.ok(ucenikService.findAllUcenici());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UcenikDTO> getUcenikById(@PathVariable Integer id) {
        return ResponseEntity.ok(ucenikService.findUcenikById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UcenikDTO> updateUcenik(@PathVariable Integer id, @RequestBody UcenikDTO ucenikDTO) {
        return ResponseEntity.ok(ucenikService.updateUcenik(id, ucenikDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUcenik(@PathVariable Integer id) {
        ucenikService.deleteUcenik(id);
        return ResponseEntity.ok().build();
    }
}