package my.application.usecases;

import java.util.List;
import java.util.Optional;

import my.entities.Transaction;

public class GetTransactionUseCase {

    public class Input {
        public String code;

        public Input (String code) {
            this.code = code;
        }
    }

    public Optional<Transaction> execute(Input input, List<Transaction> transactions) {
        return transactions.stream().filter(x -> x.getCode().equals(input.code)).findFirst();
    }
}

