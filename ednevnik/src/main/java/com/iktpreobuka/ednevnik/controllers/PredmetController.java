package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.services.PredmetService;

@RestController
@RequestMapping("/ednevnik/predmeti")
public class PredmetController {
	
	@Autowired
    private PredmetService predmetService;

    @PostMapping
    public ResponseEntity<PredmetDTO> createPredmet(@RequestBody PredmetDTO predmetDTO) {
        PredmetDTO predmet = predmetService.savePredmet(predmetDTO);
        return ResponseEntity.ok(predmet);
    }

    @GetMapping
    public ResponseEntity<List<PredmetDTO>> getAllPredmeti() {
        List<PredmetDTO> predmeti = predmetService.findAll();
        return ResponseEntity.ok(predmeti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> getPredmetById(@PathVariable Integer id) {
        PredmetDTO predmetDTO = predmetService.findById(id);
        return ResponseEntity.ok(predmetDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredmetDTO> updatePredmet(@PathVariable Integer id, @RequestBody PredmetDTO predmetDTO) {
        PredmetDTO uppredmet = predmetService.updatePredmet(id, predmetDTO);
        return ResponseEntity.ok(uppredmet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePredmet(@PathVariable Integer id) {
        predmetService.delete(id);
        return ResponseEntity.ok().build();
    }
}
