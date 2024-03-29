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
import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.NastavnikPredmetServise;
import com.iktpreobuka.ednevnik.services.PredmetService;

@RestController
@RequestMapping("/ednevnik/predmeti")
public class PredmetController {
	
	@Autowired
    private PredmetService predmetService;

    @Autowired
    private NastavnikPredmetServise nastavnikPredmetService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<PredmetDTO> dodajPredmet(@Validated @RequestBody PredmetDTO predmetDTO) {
        PredmetDTO noviPredmet = predmetService.save(predmetDTO);
        return new ResponseEntity<>(noviPredmet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    @JsonView(Views.Private.class)
    public ResponseEntity<PredmetDTO> pronadjiPredmet(@PathVariable Integer id) {
        PredmetDTO predmetDTO = predmetService.findById(id);
        return ResponseEntity.ok(predmetDTO);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<PredmetDTO> azurirajPredmet(@PathVariable Integer id,@Validated @RequestBody PredmetDTO predmetDTO) {
        PredmetDTO azuriraniPredmet = predmetService.update(id, predmetDTO);
        return ResponseEntity.ok(azuriraniPredmet);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> obrisiPredmet(@PathVariable Integer id) {
        predmetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{predmetId}/dodeli-nastavnika/{nastavnikId}")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<?> dodeliNastavnikaPredmetu(@PathVariable Integer nastavnikId,
            @PathVariable Integer predmetId) {
    	NastavnikPredmetDTO nastavnikPredmetDTO = nastavnikPredmetService.dodeliNastavnikaPredmetu(nastavnikId, predmetId);
        return new ResponseEntity<>(nastavnikPredmetDTO, HttpStatus.CREATED);
	}
    
    @GetMapping("/nastavnici-predmeti")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    @JsonView(Views.Private.class)
    public ResponseEntity<List<NastavnikPredmetDTO>> getNastavniciIPredmetiKojePredaju() {
        List<NastavnikPredmetDTO> dtoList = nastavnikPredmetService.getNastavniciIPredmetiKojePredaju();
        return ResponseEntity.ok(dtoList);
    }
    
    @GetMapping("/razred/{razredId}/predmeti")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    @JsonView(Views.Public.class)
    public ResponseEntity<List<PredmetDTO>> getPredmetiByRazredId(@PathVariable Integer razredId) {
        List<PredmetDTO> predmetiDTO = predmetService.findPredmetiByRazredId(razredId);
        return ResponseEntity.ok(predmetiDTO);
    }
    
    @DeleteMapping("/ukloni-dodelu")
    @Secured("ROLE_ADMIN")
    @JsonView(Views.Admin.class)
    public ResponseEntity<?> ukloniDodeluNastavnikaPredmetu(@RequestParam Integer nastavnikId, @RequestParam Integer predmetId) {
        boolean uklonjeno = nastavnikPredmetService.ukloniDodeluNastavnikaPredmetu(nastavnikId, predmetId);
        if (uklonjeno) {
            return new ResponseEntity<>("Dodela predmeta nastavniku uspešno uklonjena.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Dodela predmeta nastavniku nije pronađena.", HttpStatus.NOT_FOUND);
        }
    }
}
