package ru.itis.cms_homework.exceptions;

import org.springframework.http.HttpStatus;

public class CmsNotExistException extends CmsServiceException{
    public CmsNotExistException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
