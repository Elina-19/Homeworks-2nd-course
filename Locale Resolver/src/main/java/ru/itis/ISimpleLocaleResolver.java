package ru.itis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ISimpleLocaleResolver {
    void setLocale(HttpServletRequest request, HttpServletResponse response);
}
