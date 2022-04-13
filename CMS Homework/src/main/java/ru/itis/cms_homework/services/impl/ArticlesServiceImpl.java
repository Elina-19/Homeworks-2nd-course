package ru.itis.cms_homework.services.impl;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.cms_homework.dto.AddArticleForm;
import ru.itis.cms_homework.dto.ArticleDto;
import ru.itis.cms_homework.exceptions.AccountNotExistException;
import ru.itis.cms_homework.exceptions.ArticleNotFoundException;
import ru.itis.cms_homework.exceptions.CmsForbiddenException;
import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.models.Article;
import ru.itis.cms_homework.repositories.AccountsRepository;
import ru.itis.cms_homework.repositories.ArticlesRepository;
import ru.itis.cms_homework.services.ArticlesService;

import java.util.List;

import static ru.itis.cms_homework.dto.ArticleDto.*;

@RequiredArgsConstructor
@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final AccountsRepository accountsRepository;

    @Override
    public List<Article> getArticles(Account account) {
        switch (account.getRole()){
            case ADMIN:
                return articlesRepository.findAll();
            case USER:
                return articlesRepository.findArticleByIsForAdmin(false);
            default:
                return null;
        }
    }

    @Transactional
    @Override
    public void save(Long authorId, AddArticleForm article) {
        Account account = accountsRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new AccountNotExistException());

        Article newArticle = Article.builder()
                                .author(account)
                                .isForAdmin(isForAdmin(article.getIsForAdmin()))
                                .name(article.getName())
                                .text(article.getText())
                                .build();
        System.out.println(article.getName().length() + " " + article.getText().length());

        newArticle = articlesRepository.save(newArticle);

        newArticle.setSlug(
                new Slugify().slugify(newArticle.getName()) + newArticle.getId());

        articlesRepository.save(newArticle);
    }

    @Override
    public ArticleDto getById(Long id, Account account) {
        ArticleDto article = from(articlesRepository
                .findById(id)
                .orElseThrow(ArticleNotFoundException::new));

        if (article.getIsForAdmin() && !account.getRole().equals(Account.Role.ADMIN)){
            throw new CmsForbiddenException();
        }

        return article;
    }

    @Override
    public List<Article> getByName(String name) {
        return articlesRepository.findArticleByName(name);
    }

    @Override
    public ArticleDto getBySlug(String slug, Account account) {
        ArticleDto article = from(articlesRepository
                .findArticleBySlug(slug)
                .orElseThrow(ArticleNotFoundException::new));

        if (article.getIsForAdmin() && !account.getRole().equals(Account.Role.ADMIN)){
            throw new CmsForbiddenException();
        }

        return article;
    }

    //Handle value of checkbox
    private Boolean isForAdmin(String str){
        if (str == null){
            return false;
        }else {
            return true;
        }
    }
}
