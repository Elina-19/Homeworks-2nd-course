package ru.itis.services;

import ru.itis.dto.SignInDto;
import ru.itis.models.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SignInService {
    Account signIn(SignInDto form, HttpServletRequest request, HttpServletResponse response);
    boolean authenticateByUUID(HttpServletRequest request);
}
