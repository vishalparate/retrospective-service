package com.example.retrospective.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Feedback provider is required.")
    private String feedbackProvider;
    @NotNull(message = "Feedback body is required.")
    private String body;
    @Enumerated(EnumType.STRING)
    private FeedbackType type;

}
