package com.shrt.controller;

import com.shrt.model.request.ShortURLRequest;
import com.shrt.model.response.ShortURLResponse;
import com.shrt.service.URLShortnerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/shortner")
public class URLShrotnerController {

    @Autowired
    private URLShortnerService urlShortnerService;

    @PostMapping("/")
    public ResponseEntity<ShortURLResponse> shortURL(@RequestBody ShortURLRequest  shortURLRequest) {
        try {
            return ResponseEntity.ok(urlShortnerService.shortURL(shortURLRequest));
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ShortURLResponse.builder().build());
        }
    }
}
