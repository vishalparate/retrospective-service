package com.example.retrospective.service;

import com.example.retrospective.dao.RetrospectiveRepository;
import com.example.retrospective.domain.FeedbackItem;
import com.example.retrospective.domain.FeedbackType;
import com.example.retrospective.domain.Retrospective;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetrospectiveServiceTest {

    @InjectMocks
    private RetrospectiveService retrospectiveService;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;
    @Test
    public void testAddFeedbackToRetrospective() {
        Retrospective retrospective = Retrospective.builder().name("Sprint 1 Retro")
                .summary("First sprint retrospective").date(LocalDate.now())
                .participants(Arrays.asList("John", "Arya")).build();

        when(retrospectiveRepository.findById(any())).thenReturn(Optional.of(retrospective));
        when(retrospectiveRepository.save(any())).thenReturn(retrospective);

        FeedbackItem feedbackItem = new FeedbackItem();
        feedbackItem.setFeedbackProvider("John");
        feedbackItem.setBody("Good job!");
        feedbackItem.setType(FeedbackType.POSITIVE);

        Retrospective updatedRetrospective = retrospectiveService.addFeedbackToRetrospective(1L, feedbackItem);

        assertNotNull(updatedRetrospective);
    }

    @Test
    public void testUpdateFeedback() {
        FeedbackItem updatedFeedback = new FeedbackItem();
        updatedFeedback.setId(1l);
        Retrospective retrospective = Retrospective.builder().name("Sprint 1 Retro")
                .summary("First sprint retrospective").date(LocalDate.now())
                .participants(Arrays.asList("John", "Arya")).feedbackItems(Arrays.asList(updatedFeedback)).build();
        when(retrospectiveRepository.findById(any())).thenReturn(Optional.of(retrospective));
        when(retrospectiveRepository.save(any())).thenReturn(retrospective);


        updatedFeedback.setBody("Great job!");
        updatedFeedback.setType(FeedbackType.PRAISE);

        Retrospective updatedRetrospective = retrospectiveService.updateFeedback(1L, 1L, updatedFeedback);

        assertNotNull(updatedRetrospective);
    }

    @Test
    public void testGetRetrospectives() {
        Retrospective retrospective = Retrospective.builder().name("Sprint 1 Retro")
                .summary("First sprint retrospective").date(LocalDate.now())
                .participants(Arrays.asList("John", "Arya")).build();;
        when(retrospectiveRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(retrospective)));

        List<Retrospective> result = retrospectiveService.getRetrospectives(0, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
    }


}
