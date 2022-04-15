package ru.itis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.util.LanguageChangeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/test")
@Controller
public class SimpleController {

    @GetMapping
    public String index(ModelMap map, HttpServletRequest request, HttpServletResponse response){
        return "index";
    }

    @RequestMapping("/change")
    public String change(HttpServletRequest request){
        return "redirect:" + LanguageChangeUtil.getNewPath(request);
    }
}
