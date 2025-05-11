package Program.User;

import Program.Util.Conexao;
import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public void registerUser(String name, String email, String password) {
        if (password.length() <= 5) {
            System.out.println("Erro: A senha deve ter mais que 5 caracteres.");
            return;
        }

        try (Connection conn = Conexao.conectar()) {
            String checkQuery = "SELECT 1 FROM usuarios WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Erro: E-mail já registrado.");
                    return;
                }
            }

            String insertQuery = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, name);
                insertStmt.setString(2, email);
                insertStmt.setString(3, password);

                int affectedRows = insertStmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Usuário registrado: " + name);
                } else {
                    System.out.println("Nenhum registro inserido.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro no registro: " + e.getMessage());
        }
    }

    public User login(String email, String password) {
        if (email == null || password == null) {
            System.out.println("Erro: E-mail e senha são obrigatórios.");
            return null;
        }

        try (Connection conn = Conexao.conectar()) {
            String query = "SELECT name, email, password FROM usuarios WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email.toLowerCase());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (storedPassword.equals(password)) {
                        String name = rs.getString("name");
                        return new User(name, email, password);
                    } else {
                        System.out.println("Erro: Senha incorreta.");
                        return null;
                    }
                } else {
                    System.out.println("Erro: E-mail não encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.printf("Erro ao fazer login: %s%n", e.getMessage());
            return null;
        }
    }
}
