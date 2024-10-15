package my.infra.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import my.domain.repositories.ITransactionRepository;
import my.entities.Installment;
import my.entities.Transaction;

public class TransactionDatabaseRepository implements ITransactionRepository {

    private final Connection connection;

    public TransactionDatabaseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Transaction transaction) {
        final var transactionSql = "INSERT INTO transactions (id, code, value, number_installments, payment_method) " +
                        "VALUES (?, ?, ?, ?, ?)";
        try (final var stm = this.connection.prepareStatement(transactionSql)) {
            stm.setString(1, transaction.getId());
            stm.setString(2, transaction.getCode());
            stm.setDouble(3, transaction.getValue().doubleValue());
            stm.setInt(4, transaction.getNumberOfInstallments());
            stm.setString(5, transaction.getPaymentMethod());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error to create a new transaction. With message: " + e.getMessage());
        }

        final var installmentSql = "INSERT INTO installments (id, number, value, transaction_code) VALUES (?, ?, ?, ?)";
        try (final var stm = this.connection.prepareStatement(installmentSql)) {
            for (final var installment : transaction.getInstallments()) {
                stm.setString(1, installment.getId());
                stm.setInt(2, installment.getNumber());
                stm.setDouble(3, installment.getValue().doubleValue());
                stm.setString(4, transaction.getCode());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Error to create installments from transaction. With Message: " + e.getMessage());
        }
    }

    @Override
    public Optional<Transaction> findByCode(String code) {
        final var transactionSql = "SELECT id, value, number_installments, payment_method FROM transactions WHERE code = ?";
        Transaction transaction = null;
        try (final var stm = this.connection.prepareStatement(transactionSql)) {
            stm.setString(1, code);
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                transaction = new Transaction(
                    rs.getString("id"), 
                    code, 
                    rs.getBigDecimal("value"), 
                    rs.getInt("number_installments"), 
                    rs.getString("payment_method")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to find transaction by code. With message: " + e.getMessage());
        }


        final var installmentSql = "SELECT id, number, value FROM installments WHERE transaction_code = ?";
        try (final var stm = this.connection.prepareStatement(installmentSql)) {
            stm.setString(1, code);
            try (final var rs = stm.executeQuery()) {
                while (rs.next()) {
                    final var installment = new Installment(
                        rs.getString("id"), 
                        rs.getInt("number"), 
                        rs.getBigDecimal("value")
                    );
                    transaction.getInstallments().add(installment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to find transaction by code. With message: " + e.getMessage());
        }
        
        return Optional.of(transaction);
    }

}
