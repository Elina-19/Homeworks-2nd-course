package ru.itis.cms_homework.exceptions;

public class ArticleNotFoundException extends CmsNotFoundException{
    public ArticleNotFoundException() {
        super("Article not found");
    }
}
