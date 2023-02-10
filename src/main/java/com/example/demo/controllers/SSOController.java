package com.example.demo.controllers;

import com.example.demo.dtos.AuthRequest;
import com.example.demo.dtos.AuthResponse;
import com.example.demo.services.SSOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSOController {
    private final SSOService ssoService;

    public SSOController(SSOService ssoService) {
        this.ssoService = ssoService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createLoginToken(@RequestBody AuthRequest authenticationRequest) {
        return new ResponseEntity<>(ssoService.login(authenticationRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authenticationRequest) {
        return new ResponseEntity<>(ssoService.register(authenticationRequest), HttpStatus.OK);
    }
}
