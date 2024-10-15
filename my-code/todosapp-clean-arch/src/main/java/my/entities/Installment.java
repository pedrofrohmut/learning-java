package my.entities;

import java.math.BigDecimal;

public class Installment {

    private final String id;
    private final int number;
    private final BigDecimal value;

    public Installment(String id, int number, BigDecimal value) {
        this.id = id;
        this.number = number;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getValue() {
        return value;
    }

}
