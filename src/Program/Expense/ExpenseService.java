package Program.Expense;

import Entities.Expense;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

public class ExpenseService {
    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
        System.out.println("Despesa adicionada com sucesso!");
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
            return;
        }

        System.out.println("\nSuas despesas:");
        for (Expense e : expenses) {
            System.out.println("- " + e);
        }
    }

    public void clearExpenses() {
        expenses.clear();
        System.out.println("Todas as despesas foram apagadas.");
    }

    public double getTotalExpensesByMonth(Month month) {
        double total = 0.0;
        for (Expense e : expenses) {
            if (e.getDate().getMonth().equals(month)) {
                total += e.getAmount();
            }
        }
        return total;
    }

    // Método adicionado para permitir testes
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
}

