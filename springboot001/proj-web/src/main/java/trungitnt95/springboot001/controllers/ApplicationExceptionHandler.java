package trungitnt95.springboot001.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import trungitnt95.springboot001.exceptions.NoOrderInCartException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<String> resourceNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnsupportedOperationException.class})
    public ResponseEntity<String> unsupportedOperationException(UnsupportedOperationException ex) {
        return new ResponseEntity<>("error: " + ex.getMessage(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
    @ExceptionHandler(value = {NoOrderInCartException.class})
    public ResponseEntity<String> noOrderInCartException(NoOrderInCartException ex) {
        return new ResponseEntity<>("error: no order in cart exception ", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = {IllegalCallerException.class})
    public ResponseEntity<String> noOrderInCartException(IllegalCallerException ex) {
        return new ResponseEntity<>("error: this order is processed", HttpStatus.NOT_ACCEPTABLE);
    }
}
