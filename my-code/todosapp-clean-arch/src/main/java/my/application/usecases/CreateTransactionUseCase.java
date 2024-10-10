package my.application.usecases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import my.entities.Installment;
import my.entities.Transaction;

public class CreateTransactionUseCase {

    public class Input {
        public String code;
        public double value;
        public int numberOfInstallments;
        public String paymentMethod;

        public Input(String code, double value, int numberOfInstallments, String paymentMethod) {
            this.code = code;
            this.value = value;
            this.numberOfInstallments = numberOfInstallments;
            this.paymentMethod = paymentMethod;
        }
    }

    public void execute(Input form, List<Transaction> transactions) throws Exception {
        final var total = new BigDecimal(form.value);

        final var newTransaction = new Transaction(form.code, total, form.numberOfInstallments, form.paymentMethod,
                new ArrayList<Installment>());

        BigDecimal installmentValue = null;
        BigDecimal roundingDiff = null;
        try {
            final var N = new BigDecimal(form.numberOfInstallments);
            installmentValue = total.divide(N, 2, RoundingMode.FLOOR);
            final var temp = installmentValue.multiply(N);
            roundingDiff = total.subtract(temp);
        } catch (ArithmeticException e) {
            throw new Exception("Error occured while try to calculate the Installments values. " + e.getMessage());

        }

        for (int i = 1; i <= form.numberOfInstallments; i++) {
            final var thisValue = i == form.numberOfInstallments ? installmentValue.add(roundingDiff) : installmentValue;
            final var newInstallment = new Installment(i, thisValue);
            newTransaction.getInstallments().add(newInstallment);
        }

        transactions.add(newTransaction);
    }

}
