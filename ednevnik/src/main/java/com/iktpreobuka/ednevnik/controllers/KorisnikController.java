package com.iktpreobuka.ednevnik.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.KorisnikService;

@RestController
@RequestMapping("/ednevnik/korisnici")
public class KorisnikController {
	private static final Logger log = LoggerFactory.getLogger(KorisnikController.class);
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<List<KorisnikDTO>> getAllKorisnici() {
        List<KorisnikDTO> korisnici = korisnikService.findAll();
        return ResponseEntity.ok(korisnici);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<KorisnikDTO> getKorisnikById(@PathVariable Integer id) {
        KorisnikDTO korisnikDTO = korisnikService.findById(id);
        return ResponseEntity.ok(korisnikDTO);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<KorisnikDTO> createKorisnik(@Validated @RequestBody KorisnikDTO korisnikDTO) {
    	log.info("Zahtev za kreiranje novog korisnika");
    	KorisnikDTO createdKorisnik = korisnikService.save(korisnikDTO);
        return new ResponseEntity<>(createdKorisnik, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<KorisnikDTO> updateKorisnik(@PathVariable Integer id,@Validated @RequestBody KorisnikDTO korisnikDTO) {
    	log.info("Zahtev za ažuriranje korisnika sa ID: {}", id);
    	KorisnikDTO updatedKorisnik = korisnikService.update(id, korisnikDTO);
        return ResponseEntity.ok(updatedKorisnik);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Integer id) {
    	log.info("Zahtev za brisanje korisnika sa ID: {}", id);
    	korisnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/korisnickoIme/{korisnickoIme}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<KorisnikDTO> getKorisnikByKorisnickoIme(@PathVariable String korisnickoIme) {
        KorisnikDTO korisnikDTO = korisnikService.findByKorisickoIme(korisnickoIme);
        return ResponseEntity.ok(korisnikDTO);
    }
}