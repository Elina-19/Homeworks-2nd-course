package ru.itis.cms_homework.exceptions;

public class AccountNotFoundException extends CmsNotFoundException{
    public AccountNotFoundException() {
        super("Account not found");
    }
}
