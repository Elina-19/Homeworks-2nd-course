package ru.itis.service;

import ru.itis.model.Account;
import ru.itis.model.Item;

import java.util.List;

public interface AccountsService {
    List<Account> findAll();
    Account findById(Integer id);
    List<Item> findAllFavourite(Integer accountId);
    void save(Account account);
}
