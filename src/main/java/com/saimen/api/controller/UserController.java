package com.saimen.api.controller; // Sesuaikan package

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saimen.api.entity.User;
import com.saimen.api.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/upload-excel")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = auth.getName();

            User user = userRepository.findByUsername(currentUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan"));

            user.setExcelFile(file.getBytes());
            user.setExcelFileName(file.getOriginalFilename());
            userRepository.save(user);

            return ResponseEntity.ok(Map.of("message", "File Excel berhasil disimpan ke database!"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Gagal upload: " + e.getMessage()));
        }
    }
}