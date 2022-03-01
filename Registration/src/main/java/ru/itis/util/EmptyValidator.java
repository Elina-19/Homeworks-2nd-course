package ru.itis.util;

import org.springframework.stereotype.Component;

@Component
public class EmptyValidator {

    public boolean isNull(String str){
        if (str == null){
            return true;
        }
        else return false;
    }

    public boolean isEmpty(String str){
        return str.trim().isEmpty();
    }
}
