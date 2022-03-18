package ru.itis.framework.exceptions;

public class BeanNotFound extends RuntimeException{
    public BeanNotFound(){
        super();
    }

    public BeanNotFound(String message){
        super(message);
    }

    public BeanNotFound(String message, Exception e){
        super(message, e);
    }
}

