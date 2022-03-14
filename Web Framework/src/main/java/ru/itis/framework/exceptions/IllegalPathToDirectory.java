package ru.itis.framework.exceptions;

public class IllegalPathToDirectory extends RuntimeException{

    public IllegalPathToDirectory(){
        super();
    }

    public IllegalPathToDirectory(String message){
        super(message);
    }

    public IllegalPathToDirectory(String message, Exception e){
        super(message, e);
    }
}
