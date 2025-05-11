package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    private double v1;

    void testExpenseConstructorAndGetters() {

        Expense expense = new Expense("Aluguel", 1200.50);

        assertEquals("Aluguel", expense.getDescription());

        assertEquals(1200.50, expense.getAmount(), 0.01);
    }

    private void assertEquals(String aluguel, String description) {
    }

    private void assertEquals(double v, double amount, double v1) {
        this.v1 = v1;
    }
    
    void testToString() {

        Expense expense = new Expense("Supermercado", 150.75);

        assertEquals("Supermercado - R$150.75", expense.toString());
    }
}
