package ru.itis.cms_homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.cms_homework.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
