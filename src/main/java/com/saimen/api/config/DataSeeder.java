package com.saimen.api.config;

import com.saimen.api.entity.User;
import com.saimen.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(encoder.encode("admin123")); // Enkripsi password
                repo.save(user);
                System.out.println("USER ADMIN DIBUAT: admin / admin123");
            }
        };
    }
}