package com.lucasrznd.apigspot.handlers;

import com.lucasrznd.apigspot.exceptions.announcer.AnnouncerNotFoundException;
import com.lucasrznd.apigspot.exceptions.announcer.DuplicateAnnouncerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnnouncerNotFoundException.class)
    public ResponseEntity<String> handleAnnouncerNotFoundException(AnnouncerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateAnnouncerException.class)
    public ResponseEntity<String> handleDuplicateAnnouncerException(DuplicateAnnouncerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
