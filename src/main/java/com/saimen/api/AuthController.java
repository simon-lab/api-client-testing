package com.saimen.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saimen.api.entity.User;
import com.saimen.api.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @Autowired
    private UserRepository userRepository; // Panggil Repository

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        
        // 1. Cek apakah username ada di database?
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            // Username ketemu, ambil datanya
            User userDb = userOptional.get();

            // 2. Cek apakah password yang diketik sama dengan di database?
            if (userDb.getPassword().equals(loginRequest.getPassword())) {
                
                // SUKSES
                return ResponseEntity.ok()
                    .body("{\"message\": \"Login Berhasil\", \"token\": \"token-dari-db-" + userDb.getId() + "\"}");
            }
        }

        // GAGAL (Username tidak ketemu ATAU Password salah)
        return ResponseEntity.status(401)
            .body("{\"message\": \"Username atau Password Salah!\"}");
    }
}