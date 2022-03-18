package ru.itis.framework.exceptions;

public class InaccessibleException extends RuntimeException{
    public InaccessibleException(){
        super();
    }

    public InaccessibleException(String message){
        super(message);
    }

    public InaccessibleException(String message, Exception e){
        super(message, e);
    }
}

