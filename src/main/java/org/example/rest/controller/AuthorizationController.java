package org.example.rest.controller;

import org.example.rest.advice.InvalidCredentials;
import org.example.rest.advice.UnauthorizedUser;
import org.example.rest.authorities.Authorities;
import org.example.rest.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    @GetMapping("/authorize")
    public ResponseEntity<List<Authorities>> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        try {
            return ResponseEntity.ok(service.getAuthorities(user, password));
        } catch (InvalidCredentials e) {
            return ResponseEntity.badRequest().body(List.of(Authorities.valueOf(e.getMessage())));
        } catch (UnauthorizedUser e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(List.of(Authorities.valueOf(e.getMessage())));
        }
    }
}