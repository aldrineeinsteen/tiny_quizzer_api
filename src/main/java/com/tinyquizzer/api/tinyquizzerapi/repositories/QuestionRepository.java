package com.tinyquizzer.api.tinyquizzerapi.repositories;

import com.tinyquizzer.api.tinyquizzerapi.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
}