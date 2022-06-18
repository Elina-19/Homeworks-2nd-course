package ru.itis.exceptions;

import org.springframework.http.HttpStatus;

public class MainNotExistException extends MainServiceException{

    public MainNotExistException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
