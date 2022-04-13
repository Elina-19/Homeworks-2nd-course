package ru.itis.repository;

import ru.itis.model.Account;
import ru.itis.model.Item;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository {
    List<Account> findAll();
    Optional<Account> findById(Integer id);
    List<Item> findAllFavourite(Integer accountId);
    void save(Account account);
}
