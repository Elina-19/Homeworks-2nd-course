package ru.itis.cms_homework.services;

import ru.itis.cms_homework.dto.AddArticleForm;
import ru.itis.cms_homework.dto.ArticleDto;
import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.models.Article;

import java.util.List;

public interface ArticlesService {
    List<Article> getArticles(Account account);

    void save(Long authorId, AddArticleForm article);

    ArticleDto getById(Long id, Account account);

    List<Article> getByName(String name);

    ArticleDto getBySlug(String slug, Account account);
}
