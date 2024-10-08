package my.entities;

public class Transaction {
    private final String code;
    private final double value;
    private final int numberOfInstallments;
    private final String paymentMethod;

    public Transaction(String code, double value, int numberOfInstallments, String paymentMethod) {
        this.code = code;
        this.value = value;
        this.numberOfInstallments = numberOfInstallments;
        this.paymentMethod = paymentMethod;
    }

	public String getCode() {
		return code;
	}

	public double getValue() {
		return value;
	}

	public int getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
}
