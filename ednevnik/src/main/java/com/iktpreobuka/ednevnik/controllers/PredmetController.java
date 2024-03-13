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

import com.iktpreobuka.ednevnik.entities.dto.NastavnikPredmetDTO;
import com.iktpreobuka.ednevnik.entities.dto.PredmetDTO;
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
    public ResponseEntity<PredmetDTO> dodajPredmet(@Validated @RequestBody PredmetDTO predmetDTO) {
        PredmetDTO noviPredmet = predmetService.save(predmetDTO);
        return new ResponseEntity<>(noviPredmet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> pronadjiPredmet(@PathVariable Integer id) {
        PredmetDTO predmetDTO = predmetService.findById(id);
        return ResponseEntity.ok(predmetDTO);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PredmetDTO> azurirajPredmet(@PathVariable Integer id,@Validated @RequestBody PredmetDTO predmetDTO) {
        PredmetDTO azuriraniPredmet = predmetService.update(id, predmetDTO);
        return ResponseEntity.ok(azuriraniPredmet);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> obrisiPredmet(@PathVariable Integer id) {
        predmetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{predmetId}/dodeli-nastavnika/{nastavnikId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> dodeliNastavnikaPredmetu(@PathVariable Integer nastavnikId,
            @PathVariable Integer predmetId) {
    	NastavnikPredmetDTO nastavnikPredmetDTO = nastavnikPredmetService.dodeliNastavnikaPredmetu(nastavnikId, predmetId);
        return new ResponseEntity<>(nastavnikPredmetDTO, HttpStatus.CREATED);
	}
    
    @GetMapping("/razred/{razredId}/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmetiByRazredId(@PathVariable Integer razredId) {
        List<PredmetDTO> predmetiDTO = predmetService.findPredmetiByRazredId(razredId);
        return ResponseEntity.ok(predmetiDTO);
    }
}
