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

import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.services.KorisnikService;

@RestController
@RequestMapping("/ednevnik/korisnici")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<KorisnikDTO>> getAllKorisnici() {
        List<KorisnikDTO> korisnici = korisnikService.findAll();
        return ResponseEntity.ok(korisnici);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> getKorisnikById(@PathVariable Integer id) {
        KorisnikDTO korisnikDTO = korisnikService.findById(id);
        return ResponseEntity.ok(korisnikDTO);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> createKorisnik(@Validated @RequestBody KorisnikDTO korisnikDTO) {
        KorisnikDTO createdKorisnik = korisnikService.save(korisnikDTO);
        return new ResponseEntity<>(createdKorisnik, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> updateKorisnik(@PathVariable Integer id,@Validated @RequestBody KorisnikDTO korisnikDTO) {
        KorisnikDTO updatedKorisnik = korisnikService.update(id, korisnikDTO);
        return ResponseEntity.ok(updatedKorisnik);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Integer id) {
        korisnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/korisnickoIme/{korisnickoIme}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> getKorisnikByKorisnickoIme(@PathVariable String korisnickoIme) {
        KorisnikDTO korisnikDTO = korisnikService.findByKorisickoIme(korisnickoIme);
        return ResponseEntity.ok(korisnikDTO);
    }
}