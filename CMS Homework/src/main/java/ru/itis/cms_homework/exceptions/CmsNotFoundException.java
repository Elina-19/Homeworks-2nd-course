package ru.itis.cms_homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CmsNotFoundException extends CmsServiceException{
    public CmsNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
