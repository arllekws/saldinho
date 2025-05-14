package Entities;

import java.time.LocalDate;

public class Expense {
    private final String description;
    private final double amount;
    private LocalDate date;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() { return this.date; }


    @Override
    public String toString() {
        return description + " - R$" + amount;
    }
}
