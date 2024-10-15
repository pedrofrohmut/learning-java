package my.application.usecases;

import java.util.ArrayList;
import java.util.Optional;

import my.application.input.GetTransactionInput;
import my.application.output.InstallmentOutput;
import my.application.output.TransactionOutput;
import my.domain.repositories.ITransactionRepository;

public class GetTransactionUseCase {

    private final ITransactionRepository transactionRepository;

    public GetTransactionUseCase(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Optional<TransactionOutput> execute(GetTransactionInput input) {
        final var transaction =  this.transactionRepository.findByCode(input.code);

        if (!transaction.isPresent()) {
            return Optional.empty();
        }

        final var output = new TransactionOutput();
        output.code = transaction.get().getCode();
        output.value = transaction.get().getValue().doubleValue();
        output.numberOfInstallments = transaction.get().getNumberOfInstallments();
        output.paymentMethod = transaction.get().getPaymentMethod();

        output.installments = new ArrayList<InstallmentOutput>();
        for (final var installment : transaction.get().getInstallments()) {
            final var outputInstallment = new InstallmentOutput();
            outputInstallment.number = installment.getNumber();
            outputInstallment.value = installment.getValue().doubleValue();
            output.installments.add(outputInstallment);
        }

        return Optional.of(output);
    }
}

