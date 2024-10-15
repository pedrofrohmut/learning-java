package my;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import my.entities.Transaction;

@SpringBootTest
class TransactionTests {

    @Test
    void shouldGenerateInstallmentsForTransaction() {
        // Given
        final var id = UUID.randomUUID().toString();
        final var code = UUID.randomUUID().toString();
        final var transaction = new Transaction(id, code, 1000.0, 12, "credit_card");

        // When 
        try {
            transaction.generateInstallments();
        } catch (Exception e) {
            System.out.println("Error to generate installments. With message: " + e.getMessage());
        }

        // Then
        assertEquals(id, transaction.getId());
        assertEquals(code, transaction.getCode());
        assertEquals(new BigDecimal(1000.0), transaction.getValue());
        assertEquals(12, transaction.getNumberOfInstallments());
        assertEquals("credit_card", transaction.getPaymentMethod());

        final var installments = transaction.getInstallments();
        assertEquals(12, installments.size());

        final var first = installments.stream().filter(x -> x.getNumber() == 1).findFirst();
        assertTrue(first.isPresent());
        assertEquals(new BigDecimal("83.33"), first.get().getValue());

        final var last = installments.stream().filter(x -> x.getNumber() == installments.size()).findFirst();
        assertTrue(last.isPresent());
        assertEquals(new BigDecimal("83.37"), last.get().getValue());
    }

}
