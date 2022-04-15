package ru.itis.cms_homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.cms_homework.dto.AddArticleForm;
import ru.itis.cms_homework.dto.ArticleDto;
import ru.itis.cms_homework.security.details.AccountUserDetails;
import ru.itis.cms_homework.services.ArticlesService;
import ru.itis.cms_homework.utils.UserDetailsHelper;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ArticlesController {

    private final ArticlesService articlesService;

    @GetMapping("/articles")
    public String getArticlesPage(@AuthenticationPrincipal AccountUserDetails account, Model model){
        model.addAttribute("articles", articlesService.getArticles(
                UserDetailsHelper.getAccount(account)));

        return "main";
    }

    @GetMapping("/articles/newArticle")
    public String getAddArticlePage(Model model){
        model.addAttribute("addArticleForm", new AddArticleForm());

        return "addArticle";
    }

    @GetMapping("/{article-slug}")
    public String getArticleBySlug(@AuthenticationPrincipal AccountUserDetails account,
                             @PathVariable("article-slug") String slug, Model model){

        System.out.println("here");
        ArticleDto article = articlesService
                .getBySlug(slug, UserDetailsHelper.getAccount(account));

        model.addAttribute("article", article);
        return "article";
    }

    @PostMapping(("/articles/newArticle"))
    public String addArticle(@AuthenticationPrincipal AccountUserDetails account,
                             @Valid AddArticleForm addArticleForm, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("addArticleForm", addArticleForm);
            return "addArticle";
        }

        articlesService.save(
                UserDetailsHelper.getAccount(account)
                .getId(), addArticleForm);

        return "redirect:/articles";
    }
}
