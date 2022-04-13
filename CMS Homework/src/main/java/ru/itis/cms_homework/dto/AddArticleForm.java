package ru.itis.cms_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.cms_homework.models.Article;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddArticleForm {

    @Size(min = 1, max = 50, message = "Length of the article's name should be from {min} to {max}")
    private String name;

    @Size(min = 50, message = "Length of the article's content should be more than {min}")
    private String text;

    private String isForAdmin;
}
