package ru.itis.cms_homework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.cms_homework.exceptions.CmsForbiddenException;
import ru.itis.cms_homework.exceptions.CmsNotFoundException;
import ru.itis.cms_homework.validation.ValidationErrorDto;
import ru.itis.cms_homework.validation.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleValidExceptions(MethodArgumentNotValidException exception){

        List<ValidationErrorDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else {
                String objectName = error.getObjectName();
                errorDto.setObjectName(objectName);
            }

            errors.add(errorDto);
        });

        return new ResponseEntity<>(ValidationExceptionResponse.builder()
                .errors(errors)
                .build(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CmsNotFoundException.class)
    public String handleNotFoundException(){
        return "errors/notFound";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(CmsForbiddenException.class)
    public String handleForbiddenExceptions(){
        return "errors/forbidden";
    }
}
