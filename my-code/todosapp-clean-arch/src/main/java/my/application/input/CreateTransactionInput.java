package my.application.input;

public class CreateTransactionInput {

	public String code;
	public double value;
	public int numberOfInstallments;
	public String paymentMethod;

	public CreateTransactionInput(String code, double value, int numberOfInstallments, String paymentMethod) {
		this.code = code;
		this.value = value;
		this.numberOfInstallments = numberOfInstallments;
		this.paymentMethod = paymentMethod;
	}

}
