package Program.User;

import Program.Util.Conexao;
import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public void registerUser(String name, String email,  String password) {
        if (password.length() <= 5) {
            System.out.println("Erro: A senha deve ter mais que 8 caracteres.");
            return;
        }

        try (Connection conn = Conexao.conectar()) {
            // Verifica se o e-mail já existe
            String checkQuery = "SELECT 1 FROM usuarios WHERE email = ?";  // Alterado para "usuarios"
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Erro: Este e-mail já está registrado.");
                    return;
                }
            }

            // Insere o novo usuário
            String insertQuery = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";  // Alterado para "usuarios"
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, name);
                insertStmt.setString(2, email);
                insertStmt.setString(3, password);
                insertStmt.executeUpdate();
                System.out.println("Conta criada com sucesso para: " + name);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    public User login(String email, String password) {
        try (Connection conn = Conexao.conectar()) {
            String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";  // Alterado para "usuarios"
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    System.out.println("Login realizado com sucesso! Bem-vindo, " + name);
                    return new User(name, email, password);
                } else {
                    System.out.println("Erro: E-mail ou senha incorretos.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.printf("Erro ao fazer login: %s%n", e.getMessage());
            return null;
        }
    }
}
