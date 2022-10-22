package com.uid.controller;

import com.uid.model.DistributedUIDDTO;
import com.uid.service.DistributedUIDService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uid")
@Log4j2
public class UIDController {

    @Autowired
    private DistributedUIDService service;

    @GetMapping("/")
    public ResponseEntity<DistributedUIDDTO> uid() throws InterruptedException {
        return ResponseEntity.ok(service.getDistributedUID());
    }
}
