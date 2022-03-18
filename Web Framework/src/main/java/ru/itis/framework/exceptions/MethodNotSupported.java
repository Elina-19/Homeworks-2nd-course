package ru.itis.framework.exceptions;

public class MethodNotSupported extends RuntimeException{
    public MethodNotSupported(){
        super();
    }

    public MethodNotSupported(String message){
        super(message);
    }

    public MethodNotSupported(String message, Exception e){
        super(message, e);
    }
}

