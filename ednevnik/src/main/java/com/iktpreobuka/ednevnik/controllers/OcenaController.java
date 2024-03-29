package com.iktpreobuka.ednevnik.controllers;

import java.util.Map;

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
import com.iktpreobuka.ednevnik.entities.dto.DeteDTO;
import com.iktpreobuka.ednevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.ednevnik.entities.dto.ZakljucnaOcenaDTO;
import com.iktpreobuka.ednevnik.security.Views;
import com.iktpreobuka.ednevnik.services.OcenaService;

@RestController
@RequestMapping("/ednevnik/ocene")
public class OcenaController {

	
	@Autowired
	private OcenaService ocenaService;
	
	@PostMapping
	@Secured("ROLE_NASTAVNIK")
	@JsonView(Views.Private.class)
    public ResponseEntity<OcenaDTO> dodajOcenu(@Validated @RequestBody OcenaDTO ocenaDTO, DeteDTO deteDTO) {
        OcenaDTO novaOcena = ocenaService.dodajOcenu(ocenaDTO);
        return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
    }

	@PostMapping("/ocenaAdmin")
	@Secured("ROLE_ADMIN")
	@JsonView(Views.Private.class)
    public ResponseEntity<OcenaDTO> dodajOcenuAdmin(@Validated @RequestBody OcenaDTO ocenaDTO, DeteDTO deteDTO) {
        OcenaDTO novaOcena = ocenaService.dodajOcenuKaoAdmin(ocenaDTO);
        return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
    }
	
    @PutMapping("/{ocenaId}")
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    @JsonView(Views.Private.class)
    public ResponseEntity<OcenaDTO> updateOcenu(@PathVariable Integer ocenaId, @RequestBody OcenaDTO ocenaDTO) {
        OcenaDTO azuriranaOcena = ocenaService.updateOcenu(ocenaId, ocenaDTO);
        return ResponseEntity.ok(azuriranaOcena);
    }

    @DeleteMapping("/{ocenaId}")
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    @JsonView(Views.Admin.class)
    public ResponseEntity<Void> obrisiOcenu(@PathVariable Integer ocenaId) {
        ocenaService.obrisiOcenu(ocenaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ucenik/{ucenikId}")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    @JsonView(Views.Admin.class)
    public ResponseEntity<Map<String, Object>> getOcenePoPredmetimaZaUcenika(@PathVariable Integer ucenikId) {
        Map<String, Object> ocene = ocenaService.getOcenePoPredmetimaZaUcenika(ucenikId);
        return ResponseEntity.ok(ocene);
    }
    
    @PostMapping("/zakljucna")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    @JsonView(Views.Admin.class)
    public ResponseEntity<ZakljucnaOcenaDTO> dajZakljucnuOcenu(@RequestBody OcenaDTO ocenaDTO) {
    	ZakljucnaOcenaDTO zakljucenaOcenaDTO = ocenaService.dajZakljucnuOcenu(ocenaDTO.getUcenikId(), ocenaDTO.getPredmetId(), ocenaDTO.getZakljucnaOcena());
        return new ResponseEntity<>(zakljucenaOcenaDTO, HttpStatus.OK);
    }
    
    @GetMapping("/{ucenikId}/prosekZakljucnihOcena")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    @JsonView(Views.Admin.class)
    public ResponseEntity<Double> getProsekZakljucnihOcena(@PathVariable Integer ucenikId) {
       
            Double prosek = ocenaService.izracunajProsekZakljucnihOcenaZaUcenika(ucenikId);
            return ResponseEntity.ok(prosek);
        
    }
}
