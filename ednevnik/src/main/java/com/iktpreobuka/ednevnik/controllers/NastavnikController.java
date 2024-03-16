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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.entities.dto.NastavnikDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.NastavnikService;

@RestController
@RequestMapping("/ednevnik/nastavnici")
public class NastavnikController {
		@Autowired
		private NastavnikService nastavnikService;
		
		@GetMapping
		@Secured("ROLE_ADMIN")
		@JsonView(Views.Public.class)
	    public ResponseEntity<List<NastavnikDTO>> getAllNastavnici() {
	        List<NastavnikDTO> nastavnici = nastavnikService.findAll();
	        return new ResponseEntity<>(nastavnici, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    @JsonView(Views.Private.class)
	    public ResponseEntity<NastavnikDTO> getNastavnikById(@PathVariable Integer id) {
	        NastavnikDTO nastavnikDTO = nastavnikService.findById(id);
	        return new ResponseEntity<>(nastavnikDTO, HttpStatus.OK);
	    }

	    @PostMapping
	    @Secured("ROLE_ADMIN")
		@JsonView(Views.Admin.class)
	    public ResponseEntity<NastavnikDTO> createNastavnik(@Validated @RequestBody NastavnikDTO nastavnikDTO) {
	        NastavnikDTO newNastavnikDTO = nastavnikService.save(nastavnikDTO);
	        return new ResponseEntity<>(newNastavnikDTO, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    @Secured("ROLE_ADMIN")
		@JsonView(Views.Admin.class)
	    public ResponseEntity<NastavnikDTO> updateNastavnik(@PathVariable Integer id,@Validated @RequestBody NastavnikDTO nastavnikDTO) {
	        NastavnikDTO updatedNastavnikDTO = nastavnikService.update(id, nastavnikDTO);
	        return new ResponseEntity<>(updatedNastavnikDTO, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    @Secured("ROLE_ADMIN")
		@JsonView(Views.Admin.class)
	    public ResponseEntity<Void> deleteNastavnik(@PathVariable Integer id) {
	        nastavnikService.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	
	    @GetMapping("/pronadjiPoKorisnickomImenu")
	    @Secured("ROLE_ADMIN")
		@JsonView(Views.Admin.class)
	    public ResponseEntity<NastavnikDTO> pronadjiNastavnikaPoKorisnickomImenu(@RequestParam String korisnickoIme) {
	        NastavnikDTO nastavnikDTO = nastavnikService.pronadjiNastavnikaPoKorisnickomImenu(korisnickoIme);
	        return ResponseEntity.ok(nastavnikDTO);
	    }
}
