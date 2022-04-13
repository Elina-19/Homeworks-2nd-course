package ru.itis;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class SimpleLocaleResolver implements ISimpleLocaleResolver{

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String lang = requestURI.substring(0, requestURI.indexOf("."));
        System.out.println(lang);

        response.setLocale(new Locale(lang));
    }
}
