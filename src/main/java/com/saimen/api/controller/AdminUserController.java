package com.saimen.api.controller;

import com.saimen.api.entity.User;
import com.saimen.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "http://10.126.57.48:8080", allowCredentials = "true")
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();

        users.forEach(u -> {
            u.setPassword(null);
            u.setExcelFile(null);
        });

        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User userRequest) {

        if (userRequest.getUsername() == null || userRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username dan Password tidak boleh kosong!"));
        }

        if (userRequest.getUsername().length() < 4 || userRequest.getUsername().length() > 20) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username harus 4-20 karakter!"));
        }

        if (!ALPHANUMERIC_PATTERN.matcher(userRequest.getUsername()).matches()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username hanya boleh huruf dan angka!"));
        }

        if (userRequest.getPassword().length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("message", "Password minimal 6 karakter!"));
        }

        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username sudah terpakai!"));
        }

        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setRole(userRequest.getRole() != null ? userRequest.getRole() : "ROLE_CLIENT");

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User berhasil ditambahkan!"));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}/download")
    public ResponseEntity<Resource> downloadExcel(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        if (user.getExcelFile() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getExcelFileName() + "\"")
                .body(new ByteArrayResource(user.getExcelFile()));
    }
}