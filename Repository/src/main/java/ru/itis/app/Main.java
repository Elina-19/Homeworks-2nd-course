package ru.itis.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.ApplicationConfig;
import ru.itis.model.Account;
import ru.itis.service.AccountsService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        AccountsService accountsService = context.getBean(AccountsService.class);

        System.out.println(accountsService.findAll());
        System.out.println(accountsService.findById(2));
        accountsService.save(Account.builder()
                .firstName("Some")
                .lastName("Name")
                .build());
    }
}
