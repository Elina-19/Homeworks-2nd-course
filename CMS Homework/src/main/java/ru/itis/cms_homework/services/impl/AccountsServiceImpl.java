package ru.itis.cms_homework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.cms_homework.exceptions.AccountNotFoundException;
import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.repositories.AccountsRepository;
import ru.itis.cms_homework.services.AccountsService;

@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public Account getById(Long id) {
        return accountsRepository
                .findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }
}
