package Entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void testRecipeConstructorAndGetters() {
        Recipe recipe = new Recipe(500.00, LocalDate.of(2025, 4, 29), "Venda", "Venda de produtos", "Receita Fixa");

        assertEquals(500.00, recipe.value());

        assertEquals(LocalDate.of(2025, 4, 29), recipe.date());

        assertEquals("Venda", recipe.source());

        assertEquals("Venda de produtos", recipe.description());

        assertEquals("Receita Fixa", recipe.type());
    }

    @Test
    void testToString() {

        Recipe recipe = new Recipe(500.00, LocalDate.of(2025, 4, 29), "Venda", "Venda de produtos", "Receita Fixa");

        assertEquals("2025-04-29 | R$500.0 | Receita Fixa | Venda - Venda de produtos", recipe.toString());
    }
}
