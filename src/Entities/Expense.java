package Entities;

import java.time.LocalDate;

public class Expense {
    private String description;
    private double amount;
    private LocalDate date;
    private ExpenseCategory category;
    private int userID;

    // Construtor com data atual
    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
        this.category = category;
    }

    // Construtor com data manual
    public Expense(String description, double amount, LocalDate date, int userID) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.userID = userID;
    }


    public Expense(String description, double amount, LocalDate date, ExpenseCategory category, int userID) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }

    public int setUserID(int userID) {
        this.userID = userID;
        return userID;
    }

    @Override
    public String toString() {
        return description + " - R$ " + amount + " (" + date + ")";
    }
}

