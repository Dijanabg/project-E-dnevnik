package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.services.NastavnikService;

@RestController
@RequestMapping("/ednevnik/nastavnici")
public class NastavnikController {
		@Autowired
		private NastavnikService nastavnikService;
		
	    @PostMapping
	    public ResponseEntity<NastavnikDTO> saveNastavnik(@Validated @RequestBody NastavnikDTO nastavnikDTO) {
	        NastavnikDTO savedNastavnik = nastavnikService.saveNastavnik(nastavnikDTO);
	        return new ResponseEntity<>(savedNastavnik, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<List<NastavnikDTO>> findAll() {
	        List<NastavnikDTO> nastavnici = nastavnikService.findAll();
	        return new ResponseEntity<>(nastavnici, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<NastavnikDTO> findById(@PathVariable Integer id) {
	        NastavnikDTO nastavnikDTO = nastavnikService.findById(id);
	        return nastavnikDTO != null ? ResponseEntity.ok(nastavnikDTO) : ResponseEntity.notFound().build();
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<NastavnikDTO> updateNastavnik(@PathVariable Integer id, @Validated @RequestBody NastavnikDTO nastavnikDTO) {
	        NastavnikDTO updatedNastavnik = nastavnikService.updateNastavnik(id, nastavnikDTO);
	        return updatedNastavnik != null ? ResponseEntity.ok(nastavnikService.findById(id)) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteNastavnik(@PathVariable Integer id) {
	        nastavnikService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/pretraga")
	    public ResponseEntity<List<NastavnikDTO>> findByImeAndPrezime(@RequestParam String ime, @RequestParam String prezime) {
	        List<NastavnikDTO> nastavnici = nastavnikService.findByImeAndPrezime(ime, prezime);
	        return ResponseEntity.ok(nastavnici);
	    }
	
}
