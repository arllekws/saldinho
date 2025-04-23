package Program.Goal;

import Entities.FinancialGoal;
import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private final List<FinancialGoal> goals = new ArrayList<>();

    public void createGoal(FinancialGoal goal) {
        goals.add(goal);
    }

    public List<FinancialGoal> getAllGoals() {
        return goals;
    }

    public void addSavingsToGoal(FinancialGoal goal, double amount) {
        goal.addSavings(amount);
    }
}
