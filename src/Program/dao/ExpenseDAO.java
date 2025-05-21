package Program.dao;

import Entities.Expense;
import Program.database.ConnectionDataBase;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public void saveExpense(Expense expense) {
        String sql = "INSERT INTO usuarios (description, amount, date) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, expense.getDescription());
            stmt.setDouble(2, expense.getAmount());
            stmt.setDate(3, Date.valueOf(expense.getDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar a despesa: " + e.getMessage());
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT description, amount, date FROM usuarios";

        try (Connection conn = ConnectionDataBase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                LocalDate date = rs.getDate("date").toLocalDate();

                Expense expense = new Expense(description, amount, date);
                expenses.add(expense);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar despesas: " + e.getMessage());
        }

        return expenses;
    }

    public void deleteAllExpenses() {
        String sql = "DELETE FROM expenses";

        try (Connection conn = ConnectionDataBase.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsDeleted = stmt.executeUpdate(sql);
            System.out.println("Despesas apagadas do banco: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("Erro ao apagar despesas: " + e.getMessage());
        }
    }

    public double getTotalExpensesByMonth(Month month) {
        String sql = "SELECT SUM(amount) FROM expenses WHERE EXTRACT(MONTH FROM date) = ?";
        double total = 0.0;

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month.getValue()); // mês de 1 a 12
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular total do mês: " + e.getMessage());
        }

        return total;
    }

}
