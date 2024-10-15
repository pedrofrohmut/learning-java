package my.infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import my.domain.repositories.ITransactionRepository;
import my.entities.Transaction;

public class TransactionInMemoryRepository implements ITransactionRepository {

    private final List<Transaction> transactions;

    public TransactionInMemoryRepository() {
        this.transactions = new ArrayList<>();
    }

	@Override
	public void save(Transaction transaction) {
	    this.transactions.add(transaction);
	}

	@Override
	public Optional<Transaction> findByCode(String code) {
	    return this.transactions.stream().filter(x -> x.getCode().equals(code)).findFirst();
	}

}
