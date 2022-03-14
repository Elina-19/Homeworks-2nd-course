package ru.itis.framework.entities;

import ru.itis.framework.modelAndView.ModelAndView;

public interface Controller {
    String doGet(ModelAndView view);
    String doPost(ModelAndView view);
}
