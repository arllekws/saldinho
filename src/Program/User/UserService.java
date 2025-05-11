package Program.User;

import Program.Util.Conexao;
import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public void registerUser(String name, String email, String password) {
        // Validações (mantidas do seu código original)
        if (password.length() <= 5) {
            System.out.println("Erro: A senha deve ter mais que 5 caracteres.");
            return;
        }

        try (Connection conn = Conexao.conectar()) {
            // Verifica se e-mail existe
            String checkQuery = "SELECT 1 FROM usuarios WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Erro: E-mail já registrado.");
                    return;
                }
            }

            // INSERÇÃO CORRIGIDA (atenção ao password)
            String insertQuery = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, name);      // Parâmetro 1 (name)
                insertStmt.setString(2, email);     // Parâmetro 2 (email)
                insertStmt.setString(3, password);  // Parâmetro 3 (password) ← ESTAVA FALTANDO!

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
            String query = "SELECT  name, email, password FROM usuarios WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email.toLowerCase());
                try (ResultSet rs = stmt.executeQuery()) {
                }


                System.out.println("Erro: E-mail ou senha incorretos.");
                return null;
            }
        } catch (SQLException e) {
            System.err.printf("Erro ao fazer login: %s%n", e.getMessage());
            return null;
        }
    }
}