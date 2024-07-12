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
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<Authorities>> handleInvalidCredentials(InvalidCredentials e) {
        return ResponseEntity.badRequest().body(List.of(Authorities.valueOf(e.getMessage())));
    }

    @ExceptionHandler(UnauthorizedUser.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<List<Authorities>> handleUnauthorizedUser(UnauthorizedUser e) {
        System.err.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(List.of(Authorities.valueOf(e.getMessage())));
    }
}
