package com.example.part1.exception;

import java.time.LocalDateTime;
import java.util.List;

public class CustomValidationErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> errors;

    public CustomValidationErrorResponse(LocalDateTime timestamp, int status, String error, List<String> errors) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.errors = errors;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
