package ru.itis.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.Account;
import ru.itis.model.Item;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountsRepositoryImpl implements AccountsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> typedQuery = entityManager.createQuery(
                "select acc from Account acc", Account.class);

        return typedQuery.getResultList();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        TypedQuery<Account> typedQuery = entityManager.createQuery(
                "select acc from Account acc where acc.id = :id", Account.class);
        typedQuery.setParameter("id", id);

        try {
            return Optional.of(typedQuery.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAllFavourite(Integer accountId) {
        TypedQuery<Item> typedQuery = entityManager.createQuery(
                 "select item from Item item " +
                    "left join item.accounts acc " +
                     "left join item.category where acc.id = :id", Item.class);
        typedQuery.setParameter("id", accountId);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(Account account) {
        entityManager.persist(account);
    }
}
