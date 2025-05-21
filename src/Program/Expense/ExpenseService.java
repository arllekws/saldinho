package Program.Expense;

import Entities.Expense;
import Entities.ExpenseCategory;
import Program.dao.ExpenseDAO;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private final List<Expense> expenses = new ArrayList<>();
    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    public void addExpense(String description, double amount, ExpenseCategory categoria) {
        Expense expense = new Expense(description, amount);
        expenses.add(expense);
        expenseDAO.saveExpense(expense); // Salva no banco
        System.out.println("Despesa adicionada com sucesso!");
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada na sessão atual.");
            return;
        }

        System.out.println("\nSuas despesas:");
        for (Expense e : expenses) {
            System.out.println("- " + e);
        }
    }

    public void listExpensesFromDatabase() {
        List<Expense> savedExpenses = expenseDAO.getAllExpenses();

        if (savedExpenses.isEmpty()) {
            System.out.println("Nenhuma despesa encontrada no banco.");
        } else {
            System.out.println("\nDespesas salvas no banco:");
            for (Expense e : savedExpenses) {
                System.out.println("- " + e);
            }
        }
    }

    public void clearExpenses() {
        expenses.clear();
        System.out.println("Todas as despesas foram apagadas da sessão atual (memória).");
    }

    public void clearExpensesFromDatabase() {
        expenseDAO.deleteAllExpenses();
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


    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }



}
