package com.stephen.careTrack.handleExceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> globalErrors = ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(field -> field.getObjectName() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<String>();
        errors.addAll(globalErrors);
        errors.addAll(fieldErrors);

        APIError err = new APIError(LocalDateTime.now(),
                "Validation Errors" ,
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            Exception ex,
            WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        APIError err = new APIError(
                LocalDateTime.now(),
                "Constraint Violation" ,
                details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HCWNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            HCWNotFoundException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        APIError err = new APIError(
                LocalDateTime.now(),
                "HCW Not Found" ,
                details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
