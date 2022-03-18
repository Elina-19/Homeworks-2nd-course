package ru.itis.framework.exceptions;

public class EntityIsUnavailable extends RuntimeException{
    public EntityIsUnavailable(){
        super();
    }

    public EntityIsUnavailable(String message){
        super(message);
    }

    public EntityIsUnavailable(String message, Exception e){
        super(message, e);
    }
}

