package ru.itis.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MainServiceException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MainServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
