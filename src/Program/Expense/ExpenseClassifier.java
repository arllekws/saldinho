package Program.Expense;

import Entities.ExpenseCategory;

import java.util.Set;

public class ExpenseClassifier {
    private static final Set<String> nonEssentialKeywords = Set.of(
            "lazer", "cinema", "delivery", "streaming", "netflix",
            "fast food", "jogo", "roupa", "perfume", "hamburguer",
            "viagem", "spotify", "xbox", "playstation"
    );

    public static ExpenseCategory classify(String description) {
        String descLower = description.toLowerCase();
        for (String keyword : nonEssentialKeywords) {
            if (descLower.contains(keyword)) {
                return ExpenseCategory.NON_ESSENTIAL;
            }
        }
        return ExpenseCategory.ESSENTIAL;
    }
}
