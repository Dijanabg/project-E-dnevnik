package com.iktpreobuka.ednevnik.controllers;

import com.iktpreobuka.ednevnik.controllers.util.Encryption;
import com.iktpreobuka.ednevnik.entities.KorisnikEntity;
import com.iktpreobuka.ednevnik.entities.dto.KorisnikDTO;
import com.iktpreobuka.ednevnik.mappers.KorisnikMapper;
import com.iktpreobuka.ednevnik.repositories.KorisnikRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ednevnik/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired 
    private KorisnikRepository korisnikRepository;
	@Autowired
    private KorisnikMapper korisnikMapper;
	
	 @GetMapping()
	    @PreAuthorize("permitAll()")
	    public ResponseEntity<?> login(@RequestHeader("Authorization") String authHeader) {
		 log.info("Entering login method");
		 log.debug("Received authorization header: {}", authHeader);
	
		 if (authHeader != null && authHeader.startsWith("Basic")) {
	            String[] values = authHeader.split(" ", 2);
	            if (values.length == 2 && values[0].equalsIgnoreCase("Basic")) {
	                String base64Credentials = values[1];
	                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
	                String credentials = new String(credDecoded);
	                String[] userInfo = credentials.split(":", 2);

	                String korisnickoIme = userInfo[0];
	                String sifra = userInfo[1];
	                log.info("Extracted username: {}, password: {}", korisnickoIme, sifra);
	                Optional<KorisnikEntity> korisnikOpt = korisnikRepository.findByKorisnickoIme(korisnickoIme);

	                if (korisnikOpt.isPresent()) {
	                    KorisnikEntity korisnikEntity = korisnikOpt.get();
	                    log.info("Found user: {}", korisnikEntity);
	                    if (Encryption.validatePassword(sifra, korisnikEntity.getSifra())) {
	                        KorisnikDTO korisnikDTO = korisnikMapper.toDto(korisnikEntity);
	                        return ResponseEntity.ok(korisnikDTO);
	                    } else {
	                        log.info("Invalid password for user {}", korisnickoIme);
	                        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	                    }
	                } else {
	                    log.info("User with username {} not found", korisnickoIme);
	                    return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	                }
	            }
	        }
	        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	    }
	}
