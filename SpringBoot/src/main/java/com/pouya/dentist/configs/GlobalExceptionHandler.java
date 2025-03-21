package com.pouya.dentist.configs;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers,
 * converting exceptions into appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException thrown when a requested resource is not found.
     *
     * @param ex The ResourceNotFoundException that was thrown
     * @return ResponseEntity containing the exception message and HTTP 404 (NOT_FOUND) status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any uncaught exceptions that aren't specifically handled by other handlers.
     * Acts as a catch-all for unexpected errors.
     *
     * @param ex The Exception that was thrown
     * @return ResponseEntity containing the exception message and HTTP 500 (INTERNAL_SERVER_ERROR) status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}