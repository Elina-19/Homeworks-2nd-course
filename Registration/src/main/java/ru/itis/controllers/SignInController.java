package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itis.aspects.annotations.LogMethod;
import ru.itis.dto.SignInDto;
import ru.itis.exceptions.InvalidEmailException;
import ru.itis.exceptions.InvalidPasswordException;
import ru.itis.services.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/signIn")
public class SignInController {

    private final SignInService signInService;

    @GetMapping
    public String getSignInPage(){
        return "signIn";
    }

    @PostMapping
    @LogMethod
    public String signIn(SignInDto form, HttpServletRequest request, HttpServletResponse response){
        try{
            signInService.signIn(form, request, response);
            return "redirect:/profile";
        }catch (NullPointerException | InvalidEmailException | InvalidPasswordException e){
            request.setAttribute("error", e.getMessage());
            request.setAttribute("form", form);
            return "signIn";
        }
    }
}
