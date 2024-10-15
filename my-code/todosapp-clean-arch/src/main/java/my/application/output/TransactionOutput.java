package my.application.output;

import java.util.List;

public class TransactionOutput {
	public String code;
	public double value;
	public int numberOfInstallments;
	public String paymentMethod;
	public List<InstallmentOutput> installments;
}
