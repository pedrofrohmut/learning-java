package my;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import my.application.input.CreateTransactionInput;
import my.application.input.GetTransactionInput;
import my.application.usecases.CreateTransactionUseCase;
import my.application.usecases.GetTransactionUseCase;
import my.infra.repositories.TransactionDatabaseRepository;
import my.infra.repositories.TransactionInMemoryRepository;

@SpringBootTest
class CreateTransactionInteractorTests {

    @Test
    void ShouldCreateATransaction_InMemory() {
		final var transactionRepository = new TransactionInMemoryRepository();
		final var transactionCode = UUID.randomUUID().toString();

		final var createTransactionUseCase = new CreateTransactionUseCase(transactionRepository);
		final var createTransactionInput = new CreateTransactionInput(transactionCode, 1000, 12, "credit_card");
		try {
			createTransactionUseCase.execute(createTransactionInput);
		} catch (Exception e) {
			System.out.println("Error to create a Transaction. " + e.getMessage());
		}

		final var getTransactionUseCase = new GetTransactionUseCase(transactionRepository);
		final var getTransactionInput = new GetTransactionInput(transactionCode);
		final var transaction = getTransactionUseCase.execute(getTransactionInput);

		// Check Transaction
		assertTrue(transaction.isPresent());
		assertEquals(transactionCode, transaction.get().code);
		assertEquals(new BigDecimal("1000").doubleValue(), transaction.get().value);
		assertEquals("credit_card", transaction.get().paymentMethod);
		assertEquals(12, transaction.get().numberOfInstallments);

		// Check Transaction Installments
		final var installments = transaction.get().installments;
		assertEquals(12, installments.size());
		final var first = installments.stream().filter(x -> x.number == 1).findFirst();
		assertTrue(first.isPresent());
		assertEquals(new BigDecimal("83.33").doubleValue(), first.get().value);
		final var last = installments.stream().filter(x -> x.number == 12).findFirst();
		assertTrue(last.isPresent());
		assertEquals(new BigDecimal("83.37").doubleValue(), last.get().value);
	}

	@Test
    void ShouldCreateATransaction_Database() {
    	Connection connection = null;
    	try {
    		final var props = new Properties();
    		props.setProperty("user", "postgres");
    		props.setProperty("password", "password");
    		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5105/", props);
    	} catch (SQLException e) {
    		System.err.println("Could not connect to postgres database. With message: " + e.getMessage());
    	}
    	assertNotNull(connection);

		final var transactionRepository = new TransactionDatabaseRepository(connection);
		final var transactionCode = UUID.randomUUID().toString();

		final var createTransactionUseCase = new CreateTransactionUseCase(transactionRepository);
		final var createTransactionInput = new CreateTransactionInput(transactionCode, 1000, 12, "credit_card");
		try {
			createTransactionUseCase.execute(createTransactionInput);
		} catch (Exception e) {
			System.out.println("Error to create a Transaction. " + e.getMessage());
		}

		final var getTransactionUseCase = new GetTransactionUseCase(transactionRepository);
		final var getTransactionInput = new GetTransactionInput(transactionCode);
		final var transaction = getTransactionUseCase.execute(getTransactionInput);

		// Check Transaction
		assertTrue(transaction.isPresent());
		assertEquals(transactionCode, transaction.get().code);
		assertEquals(new BigDecimal("1000").doubleValue(), transaction.get().value);
		assertEquals("credit_card", transaction.get().paymentMethod);
		assertEquals(12, transaction.get().numberOfInstallments);

		// Check Transaction Installments
		final var installments = transaction.get().installments;
		assertEquals(12, installments.size());
		final var first = installments.stream().filter(x -> x.number == 1).findFirst();
		assertTrue(first.isPresent());
		assertEquals(new BigDecimal("83.33").doubleValue(), first.get().value);
		final var last = installments.stream().filter(x -> x.number == 12).findFirst();
		assertTrue(last.isPresent());
		assertEquals(new BigDecimal("83.37").doubleValue(), last.get().value);
	}
}
