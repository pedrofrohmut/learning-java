package my.controllers;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.entities.Installment;
import my.entities.Transaction;
import my.request.CreateTransactionForm;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private static List<Transaction> transactions = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<Object> createTransaction(@RequestBody CreateTransactionForm body) {
        final var total = new BigDecimal(body.value);

        final var newTransaction = new Transaction(body.code, total, body.numberOfInstallments, body.paymentMethod, new ArrayList<Installment>());

        try {
            final var N = new BigDecimal(body.numberOfInstallments);
            final var t = total.divide(N, 2, RoundingMode.FLOOR);
            final var t2 = t.multiply(N);
            final var extra = total.subtract(t2);
            for (int i = 1; i <= body.numberOfInstallments; i++) {
                final var thisValue = i == body.numberOfInstallments ? t.add(extra) : t;
                final var newInstallment = new Installment(i, thisValue);
                newTransaction.getInstallments().add(newInstallment);
            }
        } catch (ArithmeticException e) {
            return ResponseEntity.status(400).body("Could not devide the bigDecimal properly");
        }

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
