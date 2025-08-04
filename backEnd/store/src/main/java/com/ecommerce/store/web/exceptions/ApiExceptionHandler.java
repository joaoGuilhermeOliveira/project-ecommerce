package com.ecommerce.store.web.exceptions;

import com.ecommerce.store.exceptions.ConflictException;
import com.ecommerce.store.exceptions.InvalidEntityException;
import com.ecommerce.store.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex,
                        HttpServletRequest request) {
                log.error("Api Error - ", ex);
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
        }

        @ExceptionHandler(InvalidEntityException.class)
        public ResponseEntity<ErrorMessage> handleInvalidEntityException(InvalidEntityException ex,
                        HttpServletRequest request) {
                log.error("Api Error - ", ex);
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
        }

        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ErrorMessage> handleConflictException(ConflictException ex,
                        HttpServletRequest request) {
                log.error("Api Error - ", ex);
                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex,
                        HttpServletRequest request) {
                log.error("Validation error - ", ex);
                String message = ex.getConstraintViolations().stream()
                                .map(ConstraintViolation::getMessage)
                                .filter(m -> m != null && !m.isBlank())
                                .findFirst()
                                .orElse("Validation failed");
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, message));
        }
}