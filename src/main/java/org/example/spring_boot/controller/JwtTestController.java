package org.example.spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot.util.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtTestController {

    private final JwtService jwtService;

    @GetMapping("/api/check-token")
    public ResponseEntity<?> checkToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer "

        try {
            // Just extract username as a simple test
            String username = jwtService.extractUsername(token);

            return ResponseEntity.ok("Token is valid. Username: " + username);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token: " + e.getMessage());
        }
    }
}
