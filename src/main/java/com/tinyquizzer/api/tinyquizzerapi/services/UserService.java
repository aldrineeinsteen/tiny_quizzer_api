package com.tinyquizzer.api.tinyquizzerapi.services;

import com.tinyquizzer.api.tinyquizzerapi.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findByUsername(String username);

    User findById(UUID id);

    User save(User user);
}
