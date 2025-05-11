package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testExpenseConstructorAndGetters() {

        Expense expense = new Expense("Aluguel", 1200.50);

        assertEquals("Aluguel", expense.getDescription());

        assertEquals(1200.50, expense.getAmount(), 0.01);
    }

    @Test
    void testToString() {

        Expense expense = new Expense("Supermercado", 150.75);

        assertEquals("Supermercado - R$150.75", expense.toString());
    }
}
