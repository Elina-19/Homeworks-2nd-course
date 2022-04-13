package ru.itis.cms_homework.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CmsServiceException extends RuntimeException{
    private final HttpStatus httpStatus;

    public CmsServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
