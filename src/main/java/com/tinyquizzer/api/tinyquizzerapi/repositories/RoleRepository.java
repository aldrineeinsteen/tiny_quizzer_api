package com.tinyquizzer.api.tinyquizzerapi.repositories;

import com.tinyquizzer.api.tinyquizzerapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
