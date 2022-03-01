package ru.itis.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.InvalidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static final Pattern patternEmail = Pattern.compile("[A-Za-z0-9_+-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}");

    public void checkEmail(String email){
        Matcher matcher = patternEmail.matcher(email);
        if(!matcher.find()){
            throw new InvalidEmailException("Неправильный email");
        }
    }
}
