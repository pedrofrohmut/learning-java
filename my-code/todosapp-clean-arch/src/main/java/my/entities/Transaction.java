package my.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction {

    private final String id;
    private final String code;
    private final BigDecimal value;
    private final int numberOfInstallments;
    private final String paymentMethod;
    private final List<Installment> installments;

	public Transaction(String id, String code, BigDecimal value, int numberOfInstallments, String paymentMethod, List<Installment> installments) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.numberOfInstallments = numberOfInstallments;
        this.paymentMethod = paymentMethod;
        this.installments = installments;
    }

	public Transaction(String id, String code, BigDecimal value, int numberOfInstallments, String paymentMethod) {
	    this(id, code, value, numberOfInstallments, paymentMethod, new ArrayList<>());
    }

	public Transaction(String id, String code, double value, int numberOfInstallments, String paymentMethod) {
	    this(id, code, new BigDecimal(value), numberOfInstallments, paymentMethod, new ArrayList<>());
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
            final var id = UUID.randomUUID().toString();
            final var thisValue = i == this.numberOfInstallments ? installmentValue.add(roundingDiff) : installmentValue;
            final var newInstallment = new Installment(id, i, thisValue);
            this.installments.add(newInstallment);
        }
    }

    public String getId() {
        return id;
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
