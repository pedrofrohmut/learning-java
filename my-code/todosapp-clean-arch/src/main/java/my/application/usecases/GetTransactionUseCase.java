package my.application.usecases;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import my.entities.Transaction;

public class GetTransactionUseCase {

    public class Input {
        public String code;
    }

    public class OutputInstallment {
        public int number;
        public BigDecimal value;
    }

    public class Output {
        public String code;
        public BigDecimal value;
        public int numberOfInstallments;
        public String paymentMethod;
        public List<OutputInstallment> installments;
    }

    public Optional<Output> execute(Input input, List<Transaction> transactions) {
        final var transaction =  transactions.stream().filter(x -> x.getCode().equals(input.code)).findFirst();

        if (!transaction.isPresent()) {
            return Optional.empty();
        }

        final var output = new Output();
        output.code = transaction.get().getCode();
        output.value = transaction.get().getValue();
        output.numberOfInstallments = transaction.get().getNumberOfInstallments();
        output.paymentMethod = transaction.get().getPaymentMethod();
        output.installments = new ArrayList<OutputInstallment>();
        for (final var installment : transaction.get().getInstallments()) {
            final var outputInstallment = new OutputInstallment();
            outputInstallment.number = installment.getNumber();
            outputInstallment.value = installment.getValue();
            output.installments.add(outputInstallment);
        }

        return Optional.of(output);
    }
}

