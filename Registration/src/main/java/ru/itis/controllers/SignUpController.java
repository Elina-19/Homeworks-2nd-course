package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.aspects.annotations.LogMethod;
import ru.itis.dto.SignUpDto;
import ru.itis.exceptions.InvalidEmailException;
import ru.itis.exceptions.NotAvailablePasswordException;
import ru.itis.services.SignUpService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(){
        return "signUp";
    }

    @LogMethod
    @PostMapping
    public String signUp(SignUpDto form, Model model){
        try{
            signUpService.signUp(form);
            return "redirect:/signIn";
        }catch(NullPointerException | InvalidEmailException | NotAvailablePasswordException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("form", form);
            return "signUp";
        }
    }
}
