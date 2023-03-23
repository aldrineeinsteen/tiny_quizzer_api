package com.tinyquizzer.api.tinyquizzerapi.services.impl;

import com.tinyquizzer.api.tinyquizzerapi.models.User;
import com.tinyquizzer.api.tinyquizzerapi.repositories.UserRepository;
import com.tinyquizzer.api.tinyquizzerapi.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
