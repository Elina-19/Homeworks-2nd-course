package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.model.Account;
import ru.itis.model.Item;
import ru.itis.repository.AccountsRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public List<Account> findAll() {
        return accountsRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> optionalAccount = accountsRepository.findById(id);

        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        }else {
            return null;
        }
    }

    @Override
    public List<Item> findAllFavourite(Integer accountId) {
        return accountsRepository.findAllFavourite(accountId);
    }

    @Override
    public void save(Account account) {
        accountsRepository.save(account);
    }
}
