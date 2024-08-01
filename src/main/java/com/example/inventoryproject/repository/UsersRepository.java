package com.example.inventoryproject.repository;

import com.example.inventoryproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Optional<Users> findByEmail(String email);
}
