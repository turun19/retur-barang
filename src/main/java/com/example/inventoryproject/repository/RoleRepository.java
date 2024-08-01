package com.example.inventoryproject.repository;

import com.example.inventoryproject.entity.ERole;
import com.example.inventoryproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
