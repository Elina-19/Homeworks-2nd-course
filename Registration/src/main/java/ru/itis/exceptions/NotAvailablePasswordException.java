package ru.itis.exceptions;

public class NotAvailablePasswordException extends RuntimeException{
    public NotAvailablePasswordException(String message){
        super(message);
    }
}
