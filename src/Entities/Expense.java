package Entities;

import java.time.LocalDate;

public class Expense {
    private String description;
    private double amount;
    private LocalDate date;
    private ExpenseCategory category;

    // Construtor com data atual
    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
        this.category = category;
    }

    // Construtor com data manual
    public Expense(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Expense(String description, double amount, LocalDate date, ExpenseCategory category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return description + " - R$ " + amount + " (" + date + ")";
    }
}

