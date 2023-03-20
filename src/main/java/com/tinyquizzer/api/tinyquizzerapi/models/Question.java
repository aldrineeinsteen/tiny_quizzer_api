package com.tinyquizzer.api.tinyquizzerapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "complexity", nullable = false)
    private String complexity;

    @Column(name = "choice_a", nullable = false)
    private String choiceA;

    @Column(name = "choice_b", nullable = false)
    private String choiceB;

    @Column(name = "choice_c", nullable = false)
    private String choiceC;

    @Column(name = "choice_d", nullable = false)
    private String choiceD;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}

