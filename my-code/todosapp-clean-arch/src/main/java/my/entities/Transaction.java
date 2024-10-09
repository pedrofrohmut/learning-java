package my.entities;

import java.math.BigDecimal;
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
