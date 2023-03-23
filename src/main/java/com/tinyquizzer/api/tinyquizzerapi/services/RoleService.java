package com.tinyquizzer.api.tinyquizzerapi.services;

import com.tinyquizzer.api.tinyquizzerapi.model.Role;

import java.util.UUID;

public interface RoleService {
    Role findByName(String name);

    Role findById(UUID id);

    Role save(Role role);
}
