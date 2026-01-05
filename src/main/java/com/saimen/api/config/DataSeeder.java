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
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                repo.save(admin);
                System.out.println("User ADMIN berhasil dibuat.");
            }

            if (repo.findByUsername("client").isEmpty()) {
                User client = new User();
                client.setUsername("client");
                client.setPassword(encoder.encode("client123"));
                client.setRole("ROLE_CLIENT");
                repo.save(client);
                System.out.println("User CLIENT berhasil dibuat.");
            }
        };
    }
}