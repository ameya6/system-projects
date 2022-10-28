package com.shrt.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/health")
public class HealthController {


    @GetMapping("/")
    public ResponseEntity<String> health() {
        try {
            return ResponseEntity.ok("Healthy\n");
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not healthy\n");
        }
    }
}
