package Program.dao;

import Entities.User;
import Program.database.ConnectionDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean emailExiste(String email) {
        String query = "SELECT 1 FROM usuarios WHERE email = ?";
        System.out.println("📡 Conectando ao banco: ");

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar e-mail: " + e.getMessage());
            return false;
        }
    }

    public boolean salvarUsuario(User user) {
        String sql = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    public User buscarPorEmail(String email) {
        String query = "SELECT name, email, password FROM usuarios WHERE email = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email.toLowerCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            } else {
                return null;
            }



        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }
}
