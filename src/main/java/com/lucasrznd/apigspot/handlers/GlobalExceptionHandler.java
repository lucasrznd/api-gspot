package com.lucasrznd.apigspot.handlers;

import com.lucasrznd.apigspot.exceptions.announcer.AnnouncerNotFoundException;
import com.lucasrznd.apigspot.exceptions.announcer.DuplicateAnnouncerException;
import com.lucasrznd.apigspot.exceptions.company.CompanyNotFoundException;
import com.lucasrznd.apigspot.exceptions.company.DuplicateCompanyException;
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

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCompanyException.class)
    public ResponseEntity<String> handleDuplicateCompanyException(DuplicateCompanyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
