package com.tinyquizzer.api.tinyquizzerapi.services;

import com.tinyquizzer.api.tinyquizzerapi.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(UUID id);

    Category save(Category category);

    void deleteById(UUID id);
}