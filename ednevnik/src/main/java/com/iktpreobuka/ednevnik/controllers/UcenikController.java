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

import com.iktpreobuka.ednevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.ednevnik.services.UcenikService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ednevnik/ucenici")
public class UcenikController {

	@Autowired
	private UcenikService ucenikService;
	
	@GetMapping
	public ResponseEntity<List<UcenikDTO>> findAll(){
		List<UcenikDTO> ucenici = ucenikService.findAll();
		return new ResponseEntity<>(ucenici, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UcenikDTO> saveUcenik(@Validated @RequestBody UcenikDTO ucenikDTO){
		UcenikDTO savedUcenik = ucenikService.saveUcenik(ucenikDTO);
		return new ResponseEntity<>(savedUcenik, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<UcenikDTO> findById(@PathVariable Integer id) {
        UcenikDTO ucenikDTO = ucenikService.findById(id);
        return ucenikDTO != null ? ResponseEntity.ok(ucenikDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UcenikDTO> updateUcenik(@PathVariable Integer id, @Validated @RequestBody UcenikDTO ucenikDTO) {
    	UcenikDTO updatedUcenik = ucenikService.updateUcenik(id, ucenikDTO);
        return ResponseEntity.ok(updatedUcenik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUcenik(@PathVariable Integer id) {
        ucenikService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteUcenikAndKorisnik(@PathVariable Integer id) {
    	try {
            ucenikService.deleteUcenikAndKorisnik(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    @GetMapping("/pretraga")
    public ResponseEntity<List<UcenikDTO>> findByImeAndPrezime(@RequestParam String ime, @RequestParam String prezime) {
        List<UcenikDTO> ucenici = ucenikService.findByImeAndPrezime(ime, prezime);
        return ResponseEntity.ok(ucenici);
    }
}
