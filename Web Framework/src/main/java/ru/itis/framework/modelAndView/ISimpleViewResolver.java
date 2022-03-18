package ru.itis.framework.modelAndView;

import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.entities.SimpleResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public interface ISimpleViewResolver {
    void resolveViewName(ModelAndView modelAndView, SimpleRequest request, SimpleResponse response) throws ServletException, IOException;
}

