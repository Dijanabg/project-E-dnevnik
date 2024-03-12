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
import com.iktpreobuka.ednevnik.entities.dto.OdelenjeDTO;
import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.services.OdelenjeService;

@RestController
@RequestMapping("/ednevnik/odelenja")
public class OdelenjeController {

	@Autowired
	private OdelenjeService odelenjeService;


    // Dohvatanje svih odelenja
    @GetMapping
    public ResponseEntity<List<OdelenjeDTO>> getAllOdelenja() {
        return ResponseEntity.ok(odelenjeService.findAllOdelenja());
    }

    // Dohvatanje odelenja po ID
    @GetMapping("/{id}")
    public ResponseEntity<OdelenjeDTO> getOdelenjeById(@PathVariable Integer id) {
        return ResponseEntity.ok(odelenjeService.findOdelenjeById(id));
    }

    // Kreiranje novog odelenja
    @PostMapping
    public ResponseEntity<OdelenjeDTO> createOdelenje(@Validated @RequestBody OdelenjeDTO odelenjeDTO) {
        return new ResponseEntity<>(odelenjeService.createOdelenje(odelenjeDTO), HttpStatus.CREATED);
    }

    // Ažuriranje odelenja
    @PutMapping("/{id}")
    public ResponseEntity<OdelenjeDTO> updateOdelenje(@PathVariable Integer id, @Validated @RequestBody OdelenjeDTO odelenjeDTO) {
        return ResponseEntity.ok(odelenjeService.updateOdelenje(id, odelenjeDTO));
    }

    // Brisanje odelenja
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOdelenje(@PathVariable Integer id) {
        odelenjeService.deleteOdelenje(id);
        return ResponseEntity.noContent().build();
    }

    // Dodeljivanje razrednog odelenju
    @PostMapping("/{odelenjeId}/razredni/{nastavnikId}")
    public ResponseEntity<Void> setRazredniStaresina(@PathVariable Integer odelenjeId, @PathVariable Integer nastavnikId) {
        odelenjeService.setRazredniStaresina(odelenjeId, nastavnikId);
        return ResponseEntity.ok().build();
    }
    //Prikaz razrednog nekog odelenja
    @GetMapping("/{odelenjeId}/razredni")
    public ResponseEntity<NastavnikDTO> getRazredniStaresina(@PathVariable Integer odelenjeId) {
        NastavnikDTO razredniStaresina = odelenjeService.getRazredniStaresinaOdOdelenja(odelenjeId);
        return ResponseEntity.ok(razredniStaresina);
    }
 // Dodavanje učenika u odelenje
    @PostMapping("/{odelenjeId}/dodeli-ucenika")
    public ResponseEntity<UcenikDTO> dodeliUcenikaOdelenju(@PathVariable Integer odelenjeId, @RequestParam Integer ucenikId) {
        UcenikDTO ucenikDTO = odelenjeService.dodeliUcenikaOdelenju(ucenikId, odelenjeId);
        return ResponseEntity.ok(ucenikDTO);
    }
    //Uklanjanje ucenika iz odelenja
    @DeleteMapping("/ukloni-ucenika-iz-odelenja")
    public ResponseEntity<UcenikDTO> ukloniUcenikaIzOdelenja(@RequestParam Integer ucenikId) {
        UcenikDTO ucenikDTO = odelenjeService.ukloniUcenikaIzOdelenja(ucenikId);
        return ResponseEntity.ok(ucenikDTO);
    }
 // Prikaz svih učenika u odelenju
    @GetMapping("/{odelenjeId}/ucenici")
    public ResponseEntity<List<UcenikDTO>> findAllUceniciInOdelenje(@PathVariable Integer odelenjeId) {
        List<UcenikDTO> ucenici = odelenjeService.findAllUceniciInOdelenje(odelenjeId);
        return ResponseEntity.ok(ucenici);
    }
}
