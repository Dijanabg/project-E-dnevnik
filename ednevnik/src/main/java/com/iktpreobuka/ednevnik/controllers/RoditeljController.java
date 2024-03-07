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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ednevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.ednevnik.services.RoditeljService;

@RestController
@RequestMapping("/api/roditelji")
public class RoditeljController {
	
	@Autowired
	private RoditeljService roditeljService;

	@GetMapping
    public ResponseEntity<List<RoditeljDTO>> findAll() {
        List<RoditeljDTO> roditelji = roditeljService.findAll();
        return new ResponseEntity<>(roditelji, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoditeljDTO> createRoditelj(@RequestBody RoditeljDTO roditeljDTO) {
        RoditeljDTO createdRoditelj = roditeljService.saveRoditelj(roditeljDTO);
        return new ResponseEntity<>(createdRoditelj, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoditeljDTO> getRoditeljById(@PathVariable Integer id) {
        RoditeljDTO roditeljDTO = roditeljService.findById(id);
        if (roditeljDTO != null) {
            return ResponseEntity.ok(roditeljDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoditeljDTO> updateRoditelj(@PathVariable Integer id, @RequestBody RoditeljDTO roditeljDTO) {
        RoditeljDTO updatedRoditelj = roditeljService.updateNastavnik(id, roditeljDTO);
        return ResponseEntity.ok(updatedRoditelj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoditelj(@PathVariable Integer id) {
        roditeljService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<RoditeljDTO>> findByImeAndPrezime(@RequestParam String ime, @RequestParam String prezime) {
        List<RoditeljDTO> roditelji = roditeljService.findByImeAndPrezime(ime, prezime);
        return ResponseEntity.ok(roditelji);
    }

}
