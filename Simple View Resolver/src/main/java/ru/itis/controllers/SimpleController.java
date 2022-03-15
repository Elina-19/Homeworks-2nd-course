package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class SimpleController {
    @GetMapping("/nt")
    public String getNotFound(Model model){
        model.addAttribute("status", 404);
        return "status:404";
    }

    @GetMapping("/red")
    public String getRedirect(Model model){
        model.addAttribute("status", 302);
        return "status:302";
    }
}
