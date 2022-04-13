package ru.itis.cms_homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.cms_homework.security.details.AccountUserDetails;
import ru.itis.cms_homework.services.AccountsService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final AccountsService accountsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails account, Model model){
        model.addAttribute("account", accountsService.getById(account.getAccount().getId()));

        return "profile";
    }
}
