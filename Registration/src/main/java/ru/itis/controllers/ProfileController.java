package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.models.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public String getProfilePage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);
        return "profile";
    }
}
