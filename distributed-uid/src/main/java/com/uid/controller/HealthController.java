package com.uid.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Log4j2
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<String> health() {
        log.info("Healthy");
        return ResponseEntity.ok("Healthy \n");
    }
}
