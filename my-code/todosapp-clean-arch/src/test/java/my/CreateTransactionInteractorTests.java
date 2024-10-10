package my;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import my.application.usecases.CreateTransactionUseCase;
import my.application.usecases.GetTransactionUseCase;
import my.entities.Transaction;

@SpringBootTest
class CreateTransactionInteractorTests {

    @Test
    void ShouldCreateATransaction() throws Exception {
        final var transactions = new ArrayList<Transaction>();
        final var transactionCode = UUID.randomUUID().toString();

        final var createTransactionUseCase = new CreateTransactionUseCase();
        final var createTransactionInput = createTransactionUseCase.new Input(transactionCode, 1000, 12, "credit_card");
        createTransactionUseCase.execute(createTransactionInput, transactions);

        assertEquals(1, transactions.size());

        final var getTransactionUseCase = new GetTransactionUseCase();
        final var getTransactionInput = getTransactionUseCase.new Input();
        getTransactionInput.code = transactionCode;
        final var transaction = getTransactionUseCase.execute(getTransactionInput, transactions);

        // Check Transaction
        assertTrue(transaction.isPresent());
        assertEquals(transactionCode, transaction.get().code);
        assertEquals(new BigDecimal("1000"), transaction.get().value);
        assertEquals("credit_card", transaction.get().paymentMethod);
        assertEquals(12, transaction.get().numberOfInstallments);

        // Check Transaction Installments
        final var installments = transaction.get().installments;
        assertEquals(12, installments.size());
        final var first = installments.stream().filter(x -> x.number == 1).findFirst();
        assertTrue(first.isPresent());
        assertEquals(new BigDecimal("83.33"), first.get().value);
        final var last = installments.stream().filter(x -> x.number == 12).findFirst();
        assertTrue(last.isPresent());
        assertEquals(new BigDecimal("83.37"), last.get().value);
    }

}
