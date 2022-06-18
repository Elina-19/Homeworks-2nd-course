package ru.itis.cms_homework.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.cms_homework.aspects.annotations.LogMethod;
import ru.itis.cms_homework.dto.SignUpForm;
import ru.itis.cms_homework.services.SignUpService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Authentication authentication, Model model){
        if (authentication != null){
            return "redirect:/articles";
        }

        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @LogMethod
    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("signUpForm", form);
            return "signUp";
        }

        signUpService.signUp(form);
        return "redirect:/signIn";
    }
}
