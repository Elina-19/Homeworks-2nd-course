package test.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.framework.entities.Controller;
import ru.itis.framework.entities.SimpleRequestMapping;
import ru.itis.framework.modelAndView.ModelAndView;

@org.springframework.stereotype.Controller
@SimpleRequestMapping(path = "/addProduct")
public class AddProductController implements Controller {

    @Override
    public String doGet(ModelAndView view) {
        return "addProduct";
    }

    @Override
    public String doPost(ModelAndView view) {
        return null;
    }
}
