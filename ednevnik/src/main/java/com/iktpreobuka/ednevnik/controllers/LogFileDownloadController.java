package com.iktpreobuka.ednevnik.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ednevnik.security.Views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class LogFileDownloadController {

    private static final String LOG_FILE_PATH = "logs/spring-boot-logging.log";
    
    @JsonView(Views.Admin.class)
    @GetMapping("/ednevnik/show-download-page")
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    public String showDownloadPage() {
        return "downloadLog"; // ime HTML fajla bez .html ekstenzije
    }
    
    @GetMapping("ednevnik/download-log")
    @JsonView(Views.Admin.class)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_UCENIK", "ROLE_RODITELJ"})
    public ResponseEntity<Resource> downloadLogFile() throws FileNotFoundException {
        File file = new File(LOG_FILE_PATH);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=spring-boot-logging.log");
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
