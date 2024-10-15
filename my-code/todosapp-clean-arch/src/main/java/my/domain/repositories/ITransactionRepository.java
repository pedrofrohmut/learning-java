package my.domain.repositories;

import java.util.Optional;

import my.entities.Transaction;

public interface ITransactionRepository {
    void save(Transaction transaction);
    Optional<Transaction> findByCode(String code);
}
