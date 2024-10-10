package my.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.application.usecases.CreateTransactionUseCase;
import my.application.usecases.GetTransactionUseCase;
import my.entities.Transaction;
import my.request.CreateTransactionForm;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private static List<Transaction> transactions = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<Object> createTransaction(@RequestBody CreateTransactionForm body) {
        try {
            final var createTransactionUseCase = new CreateTransactionUseCase();
            final var input = createTransactionUseCase. new Input(body.code, body.value, body.numberOfInstallments, body.paymentMethod);
            createTransactionUseCase.execute(input, transactions);
            return ResponseEntity.status(201).body(null);
        } catch (ArithmeticException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getTransaction(@PathVariable String code) {
        final var getTransactionUseCase = new GetTransactionUseCase();
        final var input = getTransactionUseCase.new Input(code);
        final var foundTransaction = getTransactionUseCase.execute(input, transactions);

        if (!foundTransaction.isPresent()) {
            return ResponseEntity.status(404).body("Transaction not found");
        }

        return ResponseEntity.status(200).body(foundTransaction.get());
    }
}
