package Entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FinancialGoal {
    private final String name;
    private final double goalAmount;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private double savedAmount;

    public FinancialGoal(String name, double goalAmount, LocalDate endDate) {
        this.name = name;
        this.goalAmount = goalAmount;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.savedAmount = 0;
    }

    public double getMonthlySaving() {
        long months = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));
        if (months <= 0) {
            return goalAmount;
        }
        return (goalAmount - savedAmount) / months;
    }

    public void addSavings(double amount) {
        this.savedAmount += amount;
    }

    public double getProgressPercentage() {
        return (savedAmount / goalAmount) * 100;
    }

    public String toString() {
        return "Meta: " + name + "\nValor: R$" + goalAmount + "\nGuardado: R$" + savedAmount;
    }
}
