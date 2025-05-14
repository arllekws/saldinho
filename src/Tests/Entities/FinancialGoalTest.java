package Tests.Entities;

import Entities.FinancialGoal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialGoalTest {

    @Test
    public void testMonthlySavingCalculation() {
        LocalDate endDate = LocalDate.now().plusMonths(5);
        FinancialGoal goal = new FinancialGoal("Viajar", 5000.0, endDate);

        double expectedMonthlySaving = 1000.0;
        assertEquals(expectedMonthlySaving, goal.getMonthlySaving(), 0.001);
    }

    @Test
    public void testMonthlySavingWhenDeadlineIsNow() {
        LocalDate endDate = LocalDate.now();
        FinancialGoal goal = new FinancialGoal("Compra", 2000.0, endDate);


        assertEquals(2000.0, goal.getMonthlySaving(), 0.001);
    }

    @Test
    public void testProgressPercentage() {
        LocalDate endDate = LocalDate.now().plusMonths(3);
        FinancialGoal goal = new FinancialGoal("Curso", 1500.0, endDate);

        goal.addSavings(750.0);
        assertEquals(50.0, goal.getProgressPercentage(), 0.001);
    }

    @Test
    public void testAddSavingsAffectsMonthlySaving() {
        LocalDate endDate = LocalDate.now().plusMonths(4);
        FinancialGoal goal = new FinancialGoal("Notebook", 4000.0, endDate);

        goal.addSavings(1000.0);
        double expectedMonthly = (4000.0 - 1000.0) / 4;
        assertEquals(expectedMonthly, goal.getMonthlySaving(), 0.001);
    }

    @Test
    public void testToStringOutput() {
        LocalDate endDate = LocalDate.now().plusMonths(2);
        FinancialGoal goal = new FinancialGoal("Reserva", 1000.0, endDate);
        goal.addSavings(300.0);

        String output = goal.toString();
        assertTrue(output.contains("Meta: Reserva"));
        assertTrue(output.contains("Valor: R$1000.0"));
        assertTrue(output.contains("Guardado: R$300.0"));
    }
}
