package com.example.retrospective.service;

import com.example.retrospective.dao.RetrospectiveRepository;
import com.example.retrospective.domain.FeedbackItem;
import com.example.retrospective.domain.Retrospective;
import com.example.retrospective.exception.FeedbackItemNotFoundException;
import com.example.retrospective.exception.RetrospectiveNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RetrospectiveService {
    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    public Retrospective createRetrospective(Retrospective retrospective) {
        retrospective.setFeedbackItems(null);
        log.info("saving the retrospective : {}", retrospective.getName());
        return retrospectiveRepository.save(retrospective);
    }

    public Retrospective addFeedbackToRetrospective(Long retrospectiveId, FeedbackItem feedbackItem) {
        Retrospective retrospective = getRetrospective(retrospectiveId);

        List<FeedbackItem> feedbackItems = Optional.ofNullable(retrospective.getFeedbackItems())
                .orElse(new ArrayList<>());
        feedbackItems.add(feedbackItem);
        retrospective.setFeedbackItems(feedbackItems);
        log.info("adding the feedback for retrospective : {}", retrospective.getName());
        return retrospectiveRepository.save(retrospective);
    }


    public Retrospective updateFeedback(Long retrospectiveId, Long feedbackId, FeedbackItem updatedFeedback) {
        Retrospective retrospective = getRetrospective(retrospectiveId);

        List<FeedbackItem> feedbackItems = Optional.ofNullable(retrospective.getFeedbackItems())
                .orElseThrow(() -> new FeedbackItemNotFoundException("No Feedback items for retrospective : " + retrospective.getName() + "to update."));

        log.info("finding the feedback with id {} to update", feedbackId);
        FeedbackItem feedbackItem = feedbackItems.stream()
                .filter(item -> item.getId().equals(feedbackId))
                .findFirst()
                .orElseThrow(() -> new FeedbackItemNotFoundException("Feedback item not found with id: " + feedbackId));

        feedbackItem.setBody(updatedFeedback.getBody());
        feedbackItem.setType(updatedFeedback.getType());

        return retrospectiveRepository.save(retrospective);
    }


    public List<Retrospective> getRetrospectives(int page, int pageSize) {
        log.info("getting all the retrospectives for page : {} with  size : {}", page, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return retrospectiveRepository.findAll(pageable).stream().toList();
    }

    public List<Retrospective> searchRetrospectivesByDate(LocalDate date) {
        log.info("finding the retrospectives for date : {}", date);
        return retrospectiveRepository.findByDate(date);
    }

    private Retrospective getRetrospective(Long retrospectiveId) {
        log.info("finding the retrospective for : {}", retrospectiveId);
        return retrospectiveRepository.findById(retrospectiveId)
                .orElseThrow(() -> new RetrospectiveNotFoundException("Retrospective not found with id: " + retrospectiveId));
    }
}

