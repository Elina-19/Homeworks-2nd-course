package ru.itis.cms_homework.exceptions;

public class AccountNotExistException extends CmsNotExistException{
    public AccountNotExistException() {
        super("Account with that id does not exist");
    }
}
