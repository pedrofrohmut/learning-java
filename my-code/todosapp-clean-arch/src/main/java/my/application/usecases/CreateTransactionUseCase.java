package my.application.usecases;

import java.math.BigDecimal;

import my.application.input.CreateTransactionInput;
import my.domain.repositories.ITransactionRepository;
import my.entities.Transaction;

public class CreateTransactionUseCase {

    private final ITransactionRepository transactionRepository;

    public CreateTransactionUseCase(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
     
    public void execute(CreateTransactionInput input) throws Exception {
        final var total = new BigDecimal(input.value);

        final var newTransaction = new Transaction(input.code, total, input.numberOfInstallments, input.paymentMethod);
        newTransaction.generateInstallments();

        this.transactionRepository.save(newTransaction);
    }

}
