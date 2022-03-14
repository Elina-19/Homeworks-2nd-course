package ru.itis.framework.exceptions;

public class IllegalControllerName extends RuntimeException{

    public IllegalControllerName(){
        super();
    }

    public IllegalControllerName(String message){
        super(message);
    }

    public IllegalControllerName(String message, Exception e){
        super(message, e);
    }
}
