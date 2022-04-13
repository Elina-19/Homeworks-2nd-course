package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import ru.itis.Product;
import ru.itis.framework.annotations.methods.Get;
import ru.itis.framework.annotations.methods.Post;
import ru.itis.framework.main.SimpleController;
import ru.itis.framework.annotations.SimpleRequestMapping;
import ru.itis.framework.entities.SimpleRequest;
import ru.itis.framework.modelAndView.ModelAndView;

@Controller
@SimpleRequestMapping(path = "/addProduct")
public class AddProductController implements SimpleController {

    @Get
    public String doGet(SimpleRequest request, ModelAndView view) {
        return "addProduct";
    }

    @Post
    public String doPost(SimpleRequest request, ModelAndView view, Product product) {
        view.addAttribute("hey", 5);
        return "redirect:/products";
    }
}
