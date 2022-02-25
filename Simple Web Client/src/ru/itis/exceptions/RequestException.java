package ru.itis.exceptions;

public class RequestException extends RuntimeException{
    public RequestException(String message){
        super(message);
    }

    public RequestException(String message, Exception e){
        super(message, e);
    }
}
