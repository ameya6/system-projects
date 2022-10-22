package com.uid.controller;

import com.data.config.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Log4j2
public class HealthController {
    @Autowired
    TestService testService;

    @GetMapping("/")
    public ResponseEntity<String> health() {
        testService.test();
        log.info("Healthy");
        return ResponseEntity.ok("Healthy \n");
    }
}
