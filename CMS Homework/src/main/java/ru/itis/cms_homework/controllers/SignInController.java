package ru.itis.cms_homework.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @GetMapping
    public String getSignInPage(Authentication authentication){
        if (authentication != null){
            return "redirect:/main";
        }

        return "signIn";
    }
}
