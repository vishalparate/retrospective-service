package com.example.retrospective.dao;

import com.example.retrospective.domain.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RetrospectiveRepository extends JpaRepository<Retrospective, Long> {
    List<Retrospective> findByDate(LocalDate date);
}

