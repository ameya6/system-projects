package com.shrt.controller;

import com.shrt.model.request.UserRequest;
import com.shrt.model.response.UserResponse;
import com.shrt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortner/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
