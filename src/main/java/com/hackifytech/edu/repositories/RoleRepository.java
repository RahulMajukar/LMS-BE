package com.hackifytech.edu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackifytech.edu.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
