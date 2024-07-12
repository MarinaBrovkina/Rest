package org.example.rest.repository;

import org.example.rest.authorities.Authorities;

import java.util.List;

public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if (user.equals("testUser") && password.equals("testPassword")) {
            return List.of(Authorities.READ, Authorities.WRITE);
        }
        return List.of();
    }
}