package com.lucasrznd.apigspot.handlers;

import com.lucasrznd.apigspot.exceptions.common.*;
import com.lucasrznd.apigspot.exceptions.company.CompanyNotFoundException;
import com.lucasrznd.apigspot.exceptions.company.DuplicateCompanyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<StandardError> handleConstraintViolationException(final ConstraintViolationException ex, final HttpServletRequest request) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(UNPROCESSABLE_ENTITY.value())
                        .error(UNPROCESSABLE_ENTITY.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<StandardError> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    private ResponseEntity<StandardError> handleNameAlreadyExistsException(final NameAlreadyExistsException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    private ResponseEntity<StandardError> handlePhoneNumberAlreadyExistsException(final PhoneNumberAlreadyExistsException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(final ResourceNotFoundException ex, final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationException> handleArgumentNotValidException(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
        var error = ValidationException.builder()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message("Exception in validation attributes")
                .errors(new ArrayList<>())
                .path(request.getRequestURI()).build();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    private ResponseEntity<StandardError> handleCompanyNotFoundException(final CompanyNotFoundException ex, final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

    @ExceptionHandler(DuplicateCompanyException.class)
    private ResponseEntity<StandardError> handleDuplicateCompanyException(final DuplicateCompanyException ex, final HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(
                StandardError.builder()
                        .timestamp(LocalDateTime.now()).status(CONFLICT.value())
                        .error(NOT_FOUND.getReasonPhrase()).message(ex.getMessage())
                        .path(request.getRequestURI()).build()
        );
    }

}
