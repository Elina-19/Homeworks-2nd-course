package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.entities.SimpleController;
import ru.itis.framework.annotations.SimpleRequestMapping;
import ru.itis.framework.modelAndView.ModelAndView;

@Controller
@SimpleRequestMapping(path = "/products")
public class ProductsController implements SimpleController {

    @Get
    public String doGet(ModelAndView view) {
        return "products";
    }

    @Get
    public String doPost(ModelAndView view) {
        return null;
    }
}
