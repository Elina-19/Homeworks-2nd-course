package ru.itis.cms_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.models.Article;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {

    private String name;

    private String text;

    private Boolean isForAdmin;

    private String slug;

    private Account author;

    public static ArticleDto from(Article article){
        return ArticleDto.builder()
                .author(article.getAuthor())
                .isForAdmin(article.getIsForAdmin())
                .name(article.getName())
                .text(article.getText())
                .build();
    }

    public static List<ArticleDto> from(List<Article> articles){
        return articles.stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }
}
