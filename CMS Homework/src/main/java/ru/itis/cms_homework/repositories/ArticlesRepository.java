package ru.itis.cms_homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.cms_homework.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByIsForAdmin(Boolean bool);

    List<Article> findArticleByName(String name);

    Optional<Article> findArticleBySlug(String slug);
}
