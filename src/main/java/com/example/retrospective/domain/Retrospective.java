package com.example.retrospective.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Retrospective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String summary;
    @NotNull(message = "Date is required.")
    private LocalDate date;
    @NotEmpty(message = "At least one participant is required.")
    private List<String> participants;
    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private List<FeedbackItem> feedbackItems;

}
