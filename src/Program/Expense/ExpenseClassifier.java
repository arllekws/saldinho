package Program.Expense;

import Entities.ExpenseCategory;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class ExpenseClassifier {
    private static final Set<String> ESSENTIAL_KEYWORDS = new HashSet<>(Arrays.asList(
            "aluguel", "mercado", "supermercado", "conta", "agua", "luz",
            "internet", "remedio", "farmacia", "gasolina", "transporte", "onibus", "metro",
            "alimentacao", "saude", "medico", "hospital", "educacao", "escola", "faculdade"
    ));

    private static final Set<String> NON_ESSENTIAL_KEYWORDS = new HashSet<>(Arrays.asList(
            "cinema", "restaurante", "lanche", "shopping", "viagem", "luxo",
            "jogo", "entretenimento", "hobby", "presente", "supérfluo"
    ));

    public static boolean isEssential(String descricao) {
        String normalized = normalizeString(descricao);

        // Verifica primeiro por palavras não essenciais
        for (String keyword : NON_ESSENTIAL_KEYWORDS) {
            if (normalized.contains(keyword)) {
                return false;
            }
        }

        // Depois verifica por palavras essenciais
        for (String keyword : ESSENTIAL_KEYWORDS) {
            if (normalized.contains(keyword)) {
                return true;
            }
        }

        return false;
    }

    public static ExpenseCategory classifyExpense(String descricao) {
        String normalized = normalizeString(descricao);

        if (containsAny(normalized, NON_ESSENTIAL_KEYWORDS)) {
            return ExpenseCategory.NON_ESSENTIAL;
        }

        if (containsAny(normalized, ESSENTIAL_KEYWORDS)) {
            return ExpenseCategory.ESSENTIAL;
        }

        return null;
    }

    private static String normalizeString(String input) {
        return Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-z0-9]", " ");
    }

    private static boolean containsAny(String input, Set<String> keywords) {
        for (String keyword : keywords) {
            if (input.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
