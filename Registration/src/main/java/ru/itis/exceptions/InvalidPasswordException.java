package ru.itis.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message){
        super(message);
    }
}
