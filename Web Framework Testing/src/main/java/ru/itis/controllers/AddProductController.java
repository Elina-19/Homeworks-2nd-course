package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import ru.itis.Product;
import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.entities.SimpleController;
import ru.itis.framework.annotations.SimpleRequestMapping;
import ru.itis.framework.modelAndView.ModelAndView;

@Controller
@SimpleRequestMapping(path = "/addProduct")
public class AddProductController implements SimpleController {

    @Get
    public String doGet(ModelAndView view) {
        return "addProduct";
    }

    @Post
    public String doPost(ModelAndView view, Product product) {
        System.out.println(product.getName());
        return "products";
    }
}
