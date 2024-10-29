package com.test.Test.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Unified error handler for validation and type mismatch errors
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        // Handle validation errors (e.g., @NotNull, @NotBlank)
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) ex;
            Map<String, String> validationErrors = validationException.getBindingResult().getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            errors.putAll(validationErrors);
        }

        // Handle type mismatch errors (e.g., providing boolean instead of Long)
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException typeMismatchException = (MethodArgumentTypeMismatchException) ex;
            String error = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                    typeMismatchException.getName(),
                    typeMismatchException.getValue(),
                    typeMismatchException.getRequiredType().getSimpleName());
            errors.put(typeMismatchException.getName(), error);
        }

        // Handle JSON parsing errors (e.g., wrong data types in request body)
        if (ex instanceof HttpMessageNotReadableException) {
            Throwable cause = ((HttpMessageNotReadableException) ex).getCause();
            if (cause instanceof InvalidFormatException) {
                InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
                String fieldName = invalidFormatException.getPath().stream()
                        .map(reference -> reference.getFieldName())
                        .findFirst()
                        .orElse("Unknown field");
                String error = String.format("Invalid value '%s' for field '%s'. Expected data type is '%s'.",
                        invalidFormatException.getValue(), fieldName, invalidFormatException.getTargetType().getSimpleName());
                errors.put(fieldName, error);
            } else {
                errors.put("error", "Invalid request body format");
            }
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
