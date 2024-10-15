package my.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.application.input.CreateTransactionInput;
import my.application.input.GetTransactionInput;
import my.application.usecases.CreateTransactionUseCase;
import my.application.usecases.GetTransactionUseCase;
import my.domain.repositories.ITransactionRepository;
import my.infra.repositories.TransactionInMemoryRepository;
import my.request.CreateTransactionForm;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final ITransactionRepository transactionRepository;

    public TransactionsController() {
        this.transactionRepository = new TransactionInMemoryRepository();
    }

    @PostMapping("")
    public ResponseEntity<Object> createTransaction(@RequestBody CreateTransactionForm body) {
        try {
            final var input = new CreateTransactionInput(body.code, body.value, body.numberOfInstallments, body.paymentMethod);
            final var createTransactionUseCase = new CreateTransactionUseCase(this.transactionRepository);
            createTransactionUseCase.execute(input);
            return ResponseEntity.status(201).body(null);
        } catch (ArithmeticException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getTransaction(@PathVariable String code) {
        final var input = new GetTransactionInput(code);
        final var getTransactionUseCase = new GetTransactionUseCase(this.transactionRepository);
        final var foundTransaction = getTransactionUseCase.execute(input);

        if (!foundTransaction.isPresent()) {
            return ResponseEntity.status(404).body("Transaction not found");
        }

        return ResponseEntity.status(200).body(foundTransaction.get());
    }
}
