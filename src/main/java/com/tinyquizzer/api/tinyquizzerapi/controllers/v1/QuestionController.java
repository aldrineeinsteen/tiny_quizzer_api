package com.tinyquizzer.api.tinyquizzerapi.controllers.v1;

import com.tinyquizzer.api.tinyquizzerapi.models.Question;
import com.tinyquizzer.api.tinyquizzerapi.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<Question>> getAllQuestions(Pageable pageable) {
        Page<Question> questions = questionService.findAll(pageable);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable UUID id) {
        Optional<Optional<Question>> question = Optional.ofNullable(questionService.findById(id));
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.save(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable UUID id, @RequestBody Question updatedQuestion) {
        return questionService.findById(id).map(question -> {
            updatedQuestion.setId(id);
            Question savedQuestion = questionService.save(updatedQuestion);
            return ResponseEntity.ok(savedQuestion);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        return questionService.findById(id).map(question -> {
            questionService.deleteById(id);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
