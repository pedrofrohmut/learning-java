package my.entities;

import java.math.BigDecimal;

public class Installment {

    private final int number;
    private final BigDecimal value;

    public Installment(int number, BigDecimal value) {
        this.number = number;
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getValue() {
        return value;
    }

}
