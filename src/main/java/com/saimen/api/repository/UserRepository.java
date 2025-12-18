package com.saimen.api.repository;

import com.saimen.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA otomatis membuat query dari nama method ini
    Optional<User> findByUsername(String username);
}