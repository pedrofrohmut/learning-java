package my.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private final String code;
    private final BigDecimal value;
    private final int numberOfInstallments;
    private final String paymentMethod;
    private final List<Installment> installments;

	public Transaction(String code, BigDecimal value, int numberOfInstallments, String paymentMethod, List<Installment> installments) {
        this.code = code;
        this.value = value;
        this.numberOfInstallments = numberOfInstallments;
        this.paymentMethod = paymentMethod;
        this.installments = installments;
    }

	public Transaction(String code, BigDecimal value, int numberOfInstallments, String paymentMethod) {
	    this(code, value, numberOfInstallments, paymentMethod, new ArrayList<>());
    }

    public void generateInstallments() throws Exception {
        BigDecimal installmentValue = null;
        BigDecimal roundingDiff = null;
        try {
            final var N = new BigDecimal(this.numberOfInstallments);
            installmentValue = this.value.divide(N, 2, RoundingMode.FLOOR);
            final var temp = installmentValue.multiply(N);
            roundingDiff = this.value.subtract(temp);
        } catch (ArithmeticException e) {
            throw new Exception("Error occured while try to calculate the Installments values. " + e.getMessage());

        }

        for (int i = 1; i <= this.numberOfInstallments; i++) {
            final var thisValue = i == this.numberOfInstallments ? installmentValue.add(roundingDiff) : installmentValue;
            final var newInstallment = new Installment(i, thisValue);
            this.installments.add(newInstallment);
        }    
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<Installment> getInstallments() {
		return installments;
	}

}
