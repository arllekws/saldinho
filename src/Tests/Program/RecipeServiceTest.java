package Tests.Program;

import Entities.Recipe;
import Program.Recipe.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceTest {

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeService();
    }

    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe(100.0, LocalDate.of(2025, Month.MAY, 15), "Salary", "Job", "Income");

        recipeService.addRecipe(recipe);

        List<Recipe> allRecipes = recipeService.getAllRecipes();
        assertEquals(1, allRecipes.size());
        assertEquals(recipe, allRecipes.get(0));
    }

    @Test
    void testGetRecipesByMonth() {
        Recipe recipe1 = new Recipe(100.0, LocalDate.of(2025, Month.MAY, 15), "Salary", "Job", "Income");
        Recipe recipe2 = new Recipe(200.0, LocalDate.of(2025, Month.MAY, 20), "Freelance", "Freelance", "Income");
        Recipe recipe3 = new Recipe(150.0, LocalDate.of(2025, Month.JUNE, 10), "Freelance", "Freelance", "Income");

        recipeService.addRecipe(recipe1);
        recipeService.addRecipe(recipe2);
        recipeService.addRecipe(recipe3);

        List<Recipe> mayRecipes = recipeService.getRecipesByMonth(Month.MAY);
        assertEquals(2, mayRecipes.size());
        assertTrue(mayRecipes.contains(recipe1));
        assertTrue(mayRecipes.contains(recipe2));
        assertFalse(mayRecipes.contains(recipe3));
    }

    @Test
    void testGetRecipesByType() {
        Recipe recipe1 = new Recipe(100.0, LocalDate.of(2025, Month.MAY, 15), "Salary", "Job", "Income");
        Recipe recipe2 = new Recipe(200.0, LocalDate.of(2025, Month.MAY, 20), "Freelance", "Freelance", "Income");
        Recipe recipe3 = new Recipe(150.0, LocalDate.of(2025, Month.JUNE, 10), "Freelance", "Freelance", "Expense");

        recipeService.addRecipe(recipe1);
        recipeService.addRecipe(recipe2);
        recipeService.addRecipe(recipe3);

        List<Recipe> incomeRecipes = recipeService.getRecipesByType("Income");
        assertEquals(2, incomeRecipes.size());
        assertTrue(incomeRecipes.contains(recipe1));
        assertTrue(incomeRecipes.contains(recipe2));

        assertFalse(incomeRecipes.contains(recipe3));
    }

    @Test
    void testGetAllRecipes() {
        Recipe recipe1 = new Recipe(100.0, LocalDate.of(2025, Month.MAY, 15), "Salary", "Job", "Income");
        Recipe recipe2 = new Recipe(200.0, LocalDate.of(2025, Month.MAY, 20), "Freelance", "Freelance", "Income");

        recipeService.addRecipe(recipe1);
        recipeService.addRecipe(recipe2);

        List<Recipe> allRecipes = recipeService.getAllRecipes();
        assertEquals(2, allRecipes.size());
        assertTrue(allRecipes.contains(recipe1));
        assertTrue(allRecipes.contains(recipe2));
    }
}
