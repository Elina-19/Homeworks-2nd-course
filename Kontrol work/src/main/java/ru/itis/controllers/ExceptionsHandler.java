package ru.itis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.exceptions.MainNotExistException;
import ru.itis.validation.http.ValidationErrorDto;

@ControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MainNotExistException.class)
    public ResponseEntity<ValidationErrorDto> handleException(MainNotExistException exception) {

        ValidationErrorDto errorDto = ValidationErrorDto.builder()
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(
                errorDto, HttpStatus.BAD_REQUEST);
    }
}
