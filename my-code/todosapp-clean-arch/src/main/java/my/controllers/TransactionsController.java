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

import my.entities.Transaction;
import my.request.CreateTransactionForm;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private static List<Transaction> transactions = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<Object> createTransaction(@RequestBody CreateTransactionForm body) {
        final var newTransaction = new Transaction(body.code, body.value, body.numberOfIntallments, body.paymentMethod);
        transactions.add(newTransaction);

        final var foundTransaction = transactions.stream().filter(x -> x.getCode().equals(body.code)).findFirst();
        if (!foundTransaction.isPresent()) {
            return ResponseEntity.status(500).body("ERROR: could not add transaction");
        }

        return ResponseEntity.status(201).body(null);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getTransaction(@PathVariable String code) {
        final var foundTransaction = transactions.stream().filter(x -> x.getCode().equals(code)).findFirst();

        if (!foundTransaction.isPresent()) {
            return ResponseEntity.status(404).body("Transaction not found");
        }

        return ResponseEntity.status(200).body(foundTransaction.get());
    }

}
