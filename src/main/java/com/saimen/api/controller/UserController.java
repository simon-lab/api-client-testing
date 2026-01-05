package com.saimen.api.controller; // Sesuaikan package

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    // Ambil data user
    @GetMapping("/current-user")
    public ResponseEntity<Map<String, String>> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            String role = auth.getAuthorities().iterator().next().getAuthority();
            Map<String, String> response = new HashMap<>();
            response.put("username", auth.getName());
            response.put("role", role); // Contoh: "ROLE_ADMIN" atau "ROLE_USER"

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).build();
    }
}