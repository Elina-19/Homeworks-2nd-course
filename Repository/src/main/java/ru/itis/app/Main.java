package ru.itis.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.ApplicationConfig;
import ru.itis.model.Account;
import ru.itis.model.Category;
import ru.itis.model.CollectionsFieldsTest;
import ru.itis.model.Item;
import ru.itis.service.AccountsService;
import ru.itis.validator.CollectionFieldsValidator;
import ru.itis.validator.CollectionFieldsValidatorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        AccountsService accountsService = context.getBean(AccountsService.class);
    }
}
