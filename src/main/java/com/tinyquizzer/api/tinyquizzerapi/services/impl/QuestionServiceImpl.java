package com.tinyquizzer.api.tinyquizzerapi.services.impl;

import com.tinyquizzer.api.tinyquizzerapi.models.Question;
import com.tinyquizzer.api.tinyquizzerapi.services.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Page<Question> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Question> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Question save(Question question) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
