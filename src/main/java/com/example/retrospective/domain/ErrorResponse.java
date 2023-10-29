package com.example.retrospective.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private List<String> errors;

}
