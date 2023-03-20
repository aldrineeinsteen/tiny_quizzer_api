package com.tinyquizzer.api.tinyquizzerapi.services;

import com.tinyquizzer.api.tinyquizzerapi.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface QuestionService {

    Page<Question> findAll(Pageable pageable);

    Optional<Question> findById(UUID id);

    Question save(Question question);

    void deleteById(UUID id);

}
