package ru.itis.cms_homework.exceptions;

import org.springframework.http.HttpStatus;

public class CmsForbiddenException extends CmsServiceException{
    public CmsForbiddenException() {
        super(HttpStatus.FORBIDDEN, "You haven't access to this page");
    }
}
