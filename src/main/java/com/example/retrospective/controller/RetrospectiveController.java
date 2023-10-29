package com.example.retrospective.controller;

import com.example.retrospective.domain.FeedbackItem;
import com.example.retrospective.domain.Retrospective;
import com.example.retrospective.service.RetrospectiveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/retrospectives")
public class RetrospectiveController {
    @Autowired
    private RetrospectiveService retrospectiveService;

    @PostMapping
    public ResponseEntity<?> createRetrospective(@RequestBody @Valid Retrospective retrospective) {
        Retrospective createdRetrospective = retrospectiveService.createRetrospective(retrospective);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRetrospective);
    }

    @PostMapping("/{retrospectiveId}/feedback")
    public ResponseEntity<Retrospective> addFeedbackToRetrospective(
            @PathVariable Long retrospectiveId, @RequestBody @Valid FeedbackItem feedbackItem) {
        Retrospective updatedRetrospective = retrospectiveService.addFeedbackToRetrospective(retrospectiveId, feedbackItem);
        return ResponseEntity.ok(updatedRetrospective);
    }

    @PutMapping("/{retrospectiveId}/feedback/{feedbackId}")
    public ResponseEntity<Retrospective> updateFeedback(
            @PathVariable Long retrospectiveId, @PathVariable Long feedbackId, @RequestBody @Valid FeedbackItem updatedFeedback) {
        Retrospective updatedRetrospective = retrospectiveService.updateFeedback(retrospectiveId, feedbackId, updatedFeedback);
        return ResponseEntity.ok(updatedRetrospective);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Retrospective>> getRetrospectives(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        List<Retrospective> retrospectives = retrospectiveService.getRetrospectives(page, pageSize);
        return ResponseEntity.ok(retrospectives);
    }

    @GetMapping(path = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Retrospective>> searchRetrospectivesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Retrospective> retrospectives = retrospectiveService.searchRetrospectivesByDate(date);
        return ResponseEntity.ok(retrospectives);
    }
}

