package com.iktpreobuka.ednevnik.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.services.OcenaService;

@RestController
@RequestMapping("/ednevnik/ocene")
public class OcenaController {

	
	@Autowired
	private OcenaService ocenaService;
	
	@PostMapping
    public ResponseEntity<OcenaDTO> dodajOcenu(@Validated @RequestBody OcenaDTO ocenaDTO) {
        OcenaDTO novaOcena = ocenaService.dodajOcenu(ocenaDTO);
        return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
    }

    @PutMapping("/{ocenaId}")
    public ResponseEntity<OcenaDTO> updateOcenu(@PathVariable Integer ocenaId, @RequestBody OcenaDTO ocenaDTO) {
        OcenaDTO azuriranaOcena = ocenaService.updateOcenu(ocenaId, ocenaDTO);
        return ResponseEntity.ok(azuriranaOcena);
    }

    @DeleteMapping("/{ocenaId}")
    public ResponseEntity<Void> obrisiOcenu(@PathVariable Integer ocenaId) {
        ocenaService.obrisiOcenu(ocenaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ucenik/{ucenikId}")
    public ResponseEntity<Map<String, List<Integer>>> getOcenePoPredmetimaZaUcenika(@PathVariable Integer ucenikId) {
        Map<String, List<Integer>> ocene = ocenaService.getOcenePoPredmetimaZaUcenika(ucenikId);
        return ResponseEntity.ok(ocene);
    }
}
