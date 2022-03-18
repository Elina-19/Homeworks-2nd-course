package ru.itis.framework.exceptions;

public class RequestCouldNotBeProcessed extends RuntimeException{
    public RequestCouldNotBeProcessed(){
        super();
    }

    public RequestCouldNotBeProcessed(String message){
        super(message);
    }

    public RequestCouldNotBeProcessed(String message, Exception e){
        super(message, e);
    }
}

