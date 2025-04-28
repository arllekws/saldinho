package Program.Recipe;

import Entities.Recipe;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> getRecipesByMonth(Month month) {
        return recipes.stream()
                .filter(r -> r.date().getMonth().equals(month))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesByType(String type) {
        return recipes.stream()
                .filter(r -> r.type().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }
}
