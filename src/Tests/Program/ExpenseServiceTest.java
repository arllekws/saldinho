package Tests.Program;

import Entities.Expense;
import Program.Expense.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceTest {

    private ExpenseService expenseService;

    @BeforeEach
    public void setUp() {
        expenseService = new ExpenseService();
    }

    @Test
    public void testAddExpense() {
        expenseService.addExpense("Mercado", 150.00);
        List<Expense> expenses = expenseService.getAllExpenses();

        assertEquals(1, expenses.size());
        assertEquals("Mercado", expenses.get(0).getDescription());
        assertEquals(150.00, expenses.get(0).getAmount(), 0.001);
    }

    @Test
    public void testClearExpenses() {
        expenseService.addExpense("Internet", 100.00);
        expenseService.addExpense("Energia", 200.00);

        expenseService.clearExpenses();

        List<Expense> expenses = expenseService.getAllExpenses();
        assertTrue(expenses.isEmpty(), "As despesas deveriam estar vazias após clearExpenses()");
    }

    @Test
    public void testGetAllExpensesDoesNotExposeInternalList() {
        expenseService.addExpense("Teste", 50.00);

        List<Expense> copy = expenseService.getAllExpenses();
        copy.clear();

        assertEquals(1, expenseService.getAllExpenses().size());
    }
}
