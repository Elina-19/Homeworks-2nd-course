package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("update Account a set a.uuid = ?2 where a.email = ?1")
    void updateUuid(String email, String uuid);
}
