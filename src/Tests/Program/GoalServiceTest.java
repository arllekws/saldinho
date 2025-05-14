package Tests.Program;

import Entities.FinancialGoal;
import Program.Goal.GoalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoalServiceTest {

    private GoalService goalService;
    private FinancialGoal goal;

    @BeforeEach
    public void setUp() {
        goalService = new GoalService();
        goal = new FinancialGoal("Férias", 3000.0, LocalDate.now().plusMonths(6));
    }

    @Test
    public void testCreateGoal() {
        goalService.createGoal(goal);
        List<FinancialGoal> goals = goalService.getAllGoals();

        assertEquals(1, goals.size());
        assertEquals(goal, goals.get(0));
    }

    @Test
    public void testAddSavingsToGoal() {
        goalService.createGoal(goal);
        goalService.addSavingsToGoal(goal, 600.0);

        double expectedPercentage = (600.0 / 3000.0) * 100;
        assertEquals(expectedPercentage, goal.getProgressPercentage(), 0.001);
    }

    @Test
    public void testGetAllGoalsReturnsListWithGoals() {
        FinancialGoal goal1 = new FinancialGoal("Viagem", 2000.0, LocalDate.now().plusMonths(4));
        FinancialGoal goal2 = new FinancialGoal("Curso", 1000.0, LocalDate.now().plusMonths(2));

        goalService.createGoal(goal1);
        goalService.createGoal(goal2);

        List<FinancialGoal> goals = goalService.getAllGoals();
        assertTrue(goals.contains(goal1));
        assertTrue(goals.contains(goal2));
        assertEquals(2, goals.size());
    }
}
