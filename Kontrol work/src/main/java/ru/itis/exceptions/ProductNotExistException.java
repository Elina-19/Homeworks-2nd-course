package ru.itis.exceptions;

public class ProductNotExistException extends MainNotExistException{

    public ProductNotExistException() {
        super("Product not found");
    }

}
