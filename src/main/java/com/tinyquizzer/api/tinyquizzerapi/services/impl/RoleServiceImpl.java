package com.tinyquizzer.api.tinyquizzerapi.services.impl;

import com.tinyquizzer.api.tinyquizzerapi.model.Role;
import com.tinyquizzer.api.tinyquizzerapi.repositories.RoleRepository;
import com.tinyquizzer.api.tinyquizzerapi.services.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
