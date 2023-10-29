package com.example.retrospective.exception;

public class FeedbackItemNotFoundException extends RuntimeException{

    public FeedbackItemNotFoundException(String msg) {
        super(msg);
    }
}
